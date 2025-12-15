package org.example.colisservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.colisservice.entities.Colis;
import org.example.colisservice.entities.ColisStats;
import org.example.colisservice.service.ColisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ColisController {

    private final ColisService colisService;

    @GetMapping("/colises")
    public List<Colis> getAll() {
        return colisService.getAll();
    }

    @GetMapping("/colises/{id}")
    public Colis getById(@PathVariable Long id) {
        return colisService.getById(id);
    }

    @PostMapping("/colises/add")
    public Colis create(@RequestBody Colis colis) {
        return colisService.create(colis);
    }

    @PutMapping("/colises/{id}")
    public Colis update(@PathVariable Long id, @RequestBody Colis colis) {
        return colisService.update(id, colis);
    }

    @DeleteMapping("/colises/{id}")
    public void delete(@PathVariable Long id) {
        colisService.delete(id);
    }
    @GetMapping("/colises/stats")
    public ColisStats getStats() {
        try {
            return colisService.getStats();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur stats");
        }
    }

}
