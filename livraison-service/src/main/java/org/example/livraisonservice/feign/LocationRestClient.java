package org.example.livraisonservice.feign;

import org.example.livraisonservice.entities.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "location-service", url = "http://localhost:8090")
public interface LocationRestClient {

    @GetMapping("/location")
    Location getLocation(@RequestParam("city") String city);
}

