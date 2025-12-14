package com.example.livraisonservice.feign;

import com.example.livraisonservice.entities.Colis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "colis-service", url = "http://localhost:8092")
public interface ColisRestClient {
    @GetMapping("/colises/{id}")
    Colis getColisById(@PathVariable Long id);
}
