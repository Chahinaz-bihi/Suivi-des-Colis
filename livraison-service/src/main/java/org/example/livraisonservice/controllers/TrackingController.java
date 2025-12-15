package org.example.livraisonservice.controllers;

import org.example.livraisonservice.entities.TrackingResponse;
import org.example.livraisonservice.services.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping("/track/{id}")
    public TrackingResponse track(@PathVariable Long id) {
        return trackingService.getTracking(id);
    }
}

