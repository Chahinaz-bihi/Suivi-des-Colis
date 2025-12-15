package org.example.locationservice.services;

import org.example.locationservice.dto.NominatimResponse;
import org.example.locationservice.entities.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final WebClient webClient;
    private final Random random = new Random();

    public Location getLocation(String city) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + city + "&format=json";

        NominatimResponse[] response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(NominatimResponse[].class)
                .block();

        if (response != null && response.length > 0) {
            double baseLat = Double.parseDouble(response[0].getLat());
            double baseLon = Double.parseDouble(response[0].getLon());

            // Ajout d'un petit décalage aléatoire pour simuler le mouvement du colis
            double lat = baseLat + (random.nextDouble() - 0.5) * 0.01;
            double lon = baseLon + (random.nextDouble() - 0.5) * 0.01;

            Location loc = new Location();
            loc.setCity(city);
            loc.setLatitude(lat);
            loc.setLongitude(lon);
            return loc;
        }

        return null;
    }
}
