package org.example.livraisonservice.services;

import lombok.RequiredArgsConstructor;
import org.example.colisservice.exceptions.ColisNotFoundException;
import org.example.livraisonservice.entities.Colis;
import org.example.livraisonservice.entities.ColisLocation;
import org.example.livraisonservice.entities.Location;
import org.example.livraisonservice.entities.TrackingResponse;
import org.example.livraisonservice.exceptions.UnknownCityException;
import org.example.livraisonservice.feign.ColisRestClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrackingService {
    // 📍 Position FIXE du magasin (ex : Casablanca)
    public static final double STORE_LAT = 33.5731;
    public static final double STORE_LON = -7.5898;
    public static final String STORE_CITY = "Casablanca";

    private final ColisRestClient colisRestClient;
    private final ColisLocationService locationService;

    // Coordonnées des villes marocaines
    protected static final Map<String, double[]> CITY_COORDINATES = new HashMap<>() {{
        put(STORE_CITY, new double[]{STORE_LAT, STORE_LON});
        put("Rabat", new double[]{34.0209, -6.8416});
        put("Marrakech", new double[]{31.7917, -7.0926});
        put("Fès", new double[]{34.0531, -4.9998});
        put("Tanger", new double[]{35.7595, -5.8340});
        put("Agadir", new double[]{30.4278, -9.5981});
        put("Meknès", new double[]{33.8935, -5.5473});
        put("Oujda", new double[]{34.6867, -1.9114});
        put("Salé", new double[]{34.0531, -6.7933});
        put("Kenitra", new double[]{34.2610, -6.5802});
        put("El Jadida", new double[]{33.2316, -8.5007});
        put("Safi", new double[]{32.2994, -9.2372});
        put("Nador", new double[]{35.1681, -2.9333});
        put("Mohamadia", new double[]{33.6833, -7.4167});
        put("El Kelaa des Sraghna", new double[]{31.5270, -8.6511}); // si nécessaire
        put("Béni Mellal", new double[]{32.3373, -6.3498}); // si nécessaire
    }};

    public TrackingResponse getTracking(Long colisId) {

        Colis colis = colisRestClient.getColisById(colisId);
        if (colis == null) {
            throw new ColisNotFoundException(colisId);
        }
        ColisLocation loc = locationService.getLocation(colisId);

        // DESTINATION = ville du destinataire (ex: Rabat)
        double[] dest = findCityCoordinates(colis.getDestinataire());

        if (dest == null) {
            throw new UnknownCityException(colis.getDestinataire());
        }

        if (loc == null) {
            loc = locationService.initColis(
                    colisId,
                    STORE_LAT,
                    STORE_LON,
                    dest[0],
                    dest[1]
            );
        } else {
            loc = locationService.updateLocation(colisId); // <- met à jour la position ET le statut
        }


        Location location = new Location();
        location.setCity(STORE_CITY);
        location.setLatitude(loc.getLatitude());
        location.setLongitude(loc.getLongitude());

        TrackingResponse response = new TrackingResponse();
        response.setColis(colis);
        response.setLocation(location);
        response.setStatut(colis.getStatut());

        return response;
    }



    private static final double[] EMPTY_COORDINATES = new double[0];

    private double[] findCityCoordinates(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            return EMPTY_COORDINATES;
        }

        for (Map.Entry<String, double[]> e : CITY_COORDINATES.entrySet()) {
            if (e.getKey().equalsIgnoreCase(cityName.trim())) {
                return e.getValue();
            }
        }
        return EMPTY_COORDINATES;
    }

}
