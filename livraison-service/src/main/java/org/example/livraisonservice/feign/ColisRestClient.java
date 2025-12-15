package org.example.livraisonservice.feign;

import org.example.livraisonservice.entities.Colis;
import org.example.livraisonservice.entities.ColisLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "colis-service", url = "http://localhost:8088/api")
public interface ColisRestClient {
    @GetMapping("colises/{id}")
    Colis getColisById(@PathVariable Long id);
    @PutMapping("colises/{id}")
    Colis updateColis(@PathVariable Long id, @RequestBody Colis colis);
    @GetMapping("colises")
    List<Colis> getAllColis();
    @PutMapping("/colises/{id}")
    Colis updateColis(@PathVariable("id") Long id, @RequestBody ColisLocation location);

}
