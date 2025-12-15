package org.example.livraisonservice.entities;

import lombok.*;
import org.example.livraisonservice.StatusLivraison;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colis {
    private Long id;
    private String reference;
    private String expediteur;
    private String destinataire;
    private double poids;
    private StatusLivraison statut;
}
