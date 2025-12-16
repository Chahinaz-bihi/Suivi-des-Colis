package org.example.colisservice.service;

import lombok.RequiredArgsConstructor;
import org.example.colisservice.entities.Colis;
import org.example.colisservice.entities.ColisStats;
import org.example.colisservice.exceptions.ColisAlreadyDeliveredException;
import org.example.colisservice.exceptions.ColisNotFoundException;
import org.example.livraisonservice.StatusLivraison;
import org.example.colisservice.repositories.ColisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColisService {

    private final ColisRepository colisRepository;
    private final EmailService emailService;

    public List<Colis> getAll() {
        return colisRepository.findAll();
    }

    public Colis getById(Long id) {
        return colisRepository.findById(id)
                .orElseThrow(() ->new ColisNotFoundException(id));
    }

    public Colis create(Colis colis) {
        return colisRepository.save(colis);
    }

    public void delete(Long id) {
        colisRepository.deleteById(id);
    }

    public Colis update(Long id, Colis updatedColis) {
        Colis colis = getById(id);

        // ❌ Vérification si déjà livré
        if (colis.getStatut() == StatusLivraison.DELIVERED) {
            throw new ColisAlreadyDeliveredException(id);
        }

        // 🔎 ancien statut
        StatusLivraison oldStatut = colis.getStatut();

        colis.setReference(updatedColis.getReference());
        colis.setExpediteur(updatedColis.getExpediteur());
        colis.setDestinataire(updatedColis.getDestinataire());
        colis.setPoids(updatedColis.getPoids());
        colis.setStatut(updatedColis.getStatut());

        Colis saved = colisRepository.save(colis);

        // 📧 ENVOI EMAIL SI LIVRÉ
        if (oldStatut != StatusLivraison.DELIVERED
                && saved.getStatut() == StatusLivraison.DELIVERED) {

            emailService.sendColisDeliveredEmail(
                    "bihichahinaz@gmail.com", // 👉 mets ton email
                    saved
            );
        }

        return saved;
    }
    public ColisStats getStats() {
        List<Colis> colisList = colisRepository.findAll();

        long enCours = colisList.stream()
                .filter(c -> c.getStatut() == StatusLivraison.PREPARED)
                .count();

        long delivered = colisList.stream()
                .filter(c -> c.getStatut() == StatusLivraison.DELIVERED)
                .count();

        long outForDelivery = colisList.stream()
                .filter(c -> c.getStatut() == StatusLivraison.OUT_FOR_DELIVERY)
                .count();

        double poidsTotal = colisList.stream()
                .mapToDouble(Colis::getPoids)
                .sum();

        ColisStats stats = new ColisStats();
        stats.setEnCours(enCours);
        stats.setDelivered(delivered);
        stats.setOutForDelivery(outForDelivery);
        stats.setPoidsTotal(poidsTotal);

        return stats;
    }


}

