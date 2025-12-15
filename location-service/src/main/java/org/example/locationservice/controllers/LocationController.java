package org.example.locationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.locationservice.entities.Location;
import org.example.locationservice.services.GeoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final GeoService locationService;

    @GetMapping("/location")
    public Location getLocation(@RequestParam String city) {
        return locationService.getLocation(city);
    }
}

