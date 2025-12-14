package org.example.livraisonservice.services;

import org.example.livraisonservice.entities.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
@RequiredArgsConstructor
public class GeoService {

    private final WebClient webClient;

    public Location getLocation(String city) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + city + "&format=json";

        Location[] response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Location[].class)
                .block();

        if (response != null && response.length > 0) {
            Location loc = new Location();
            loc.setLat(response[0].getLat());
            loc.setLon(response[0].getLon());
            loc.setCity(city);
            return loc;
        }

        return null;
    }
}

