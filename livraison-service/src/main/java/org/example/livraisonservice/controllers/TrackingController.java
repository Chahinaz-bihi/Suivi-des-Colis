package com.example.livraisonservice.controllers;

import com.example.livraisonservice.entities.TrackingResponse;
import com.example.livraisonservice.services.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

