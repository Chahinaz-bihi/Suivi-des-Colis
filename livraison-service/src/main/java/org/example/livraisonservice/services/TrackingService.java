package com.example.livraisonservice.services;

import com.example.livraisonservice.StatusLivraison;
import com.example.livraisonservice.entities.Colis;
import com.example.livraisonservice.entities.TrackingResponse;
import com.example.livraisonservice.feign.ColisRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final ColisRestClient colisRestClient;
    private final GeoService geoService;

    public TrackingResponse getTracking(Long colisId) {
        Colis colis = colisRestClient.getColisById(colisId);

        TrackingResponse response = new TrackingResponse();
        response.setColis(colis);
        response.setStatut(StatusLivraison.IN_TRANSIT);
        response.setLocation(geoService.getLocation(colis.getDestinataire()));

        return response;
    }
}

