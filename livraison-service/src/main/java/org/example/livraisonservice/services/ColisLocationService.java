package org.example.livraisonservice.services;

import lombok.RequiredArgsConstructor;
import org.example.livraisonservice.StatusLivraison;
import org.example.livraisonservice.entities.Colis;
import org.example.livraisonservice.entities.ColisLocation;
import org.springframework.stereotype.Service;
import org.example.livraisonservice.feign.ColisRestClient;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RequiredArgsConstructor
public class ColisLocationService {
    private final ColisRestClient colisRestClient;
    private final Map<Long, ColisLocation> locations = new HashMap<>();
    /**
     * Initialisation du colis :
     * - position de départ = expéditeur
     * - destination = destinataire
     */
    public ColisLocation initColis(
            Long colisId,
            double srcLat,
            double srcLon,
            double destLat,
            double destLon
    ) {
        ColisLocation loc = new ColisLocation(
                colisId,
                srcLat,
                srcLon,
                destLat,
                destLon
        );

        locations.put(colisId, loc);

        logger.info("📦 Colis " + colisId + " initialisé");
        logger.info("   ➜ Source      : " + srcLat + ", " + srcLon);
        logger.info("   ➜ Destination : " + destLat + ", " + destLon);

        return loc;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(ColisLocationService.class);


    /**
     * Déplacement progressif vers la destination
     */
    public ColisLocation updateLocation(Long colisId) {
        ColisLocation loc = locations.get(colisId);
        if (loc == null) {
            // Récupère le colis pour connaitre la destination
            Colis colis = colisRestClient.getColisById(colisId);
            double[] dest = TrackingService.CITY_COORDINATES.get(colis.getDestinataire());
            loc = initColis(colisId, TrackingService.STORE_LAT, TrackingService.STORE_LON, dest[0], dest[1]);
        }


        Colis colis = colisRestClient.getColisById(colisId);
        if (colis == null) return null;

        double currentLat = loc.getLatitude();
        double currentLon = loc.getLongitude();
        double destLat = loc.getDestLatitude();
        double destLon = loc.getDestLongitude();

        // Vitesse : 5% du trajet restant
        double speed = 0.05;

        double newLat = currentLat + (destLat - currentLat) * speed;
        double newLon = currentLon + (destLon - currentLon) * speed;

        boolean arrived = false;

        // Arrêt précis à destination
        if (Math.abs(destLat - newLat) < 0.0001 && Math.abs(destLon - newLon) < 0.0001) {
            loc.setLatitude(destLat);
            loc.setLongitude(destLon);
            arrived = true;
            colis.setStatut(StatusLivraison.DELIVERED); // Colis arrivé
            logger.info("✅ Colis " + colisId + " arrivé à destination");
        } else {
            loc.setLatitude(newLat);
            loc.setLongitude(newLon);
            if (colis.getStatut() == StatusLivraison.PREPARED) {
                colis.setStatut(StatusLivraison.OUT_FOR_DELIVERY); // En cours de livraison
            }
            logger.info("🚚 Colis " + colisId + " en déplacement → "
                    + String.format("%.5f", newLat) + ", "
                    + String.format("%.5f", newLon));
        }

        // Mise à jour du colis dans ColisService via FeignClient
        colisRestClient.updateColis(colisId, colis);

        return loc;
    }



    public ColisLocation getLocation(Long colisId) {
        return locations.get(colisId);
    }

    public boolean isTracked(Long colisId) {
        return locations.containsKey(colisId);
    }

    public void resetAllLocations() {
        locations.clear();
    }

    public void removeLocation(Long colisId) {
        locations.remove(colisId);
    }

    public int getTrackedColisCount() {
        return locations.size();
    }

    public Map<Long, ColisLocation> getAllLocations() {
        return new HashMap<>(locations);
    }
}
