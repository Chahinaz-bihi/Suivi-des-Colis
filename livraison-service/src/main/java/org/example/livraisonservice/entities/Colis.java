package com.example.livraisonservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colis {
    private Long id;
    private String reference;
    private String expediteur;
    private String destinataire;
    private double poids;
}
