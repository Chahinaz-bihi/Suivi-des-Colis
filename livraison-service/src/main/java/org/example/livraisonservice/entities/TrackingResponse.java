package com.example.livraisonservice.entities;

import com.example.livraisonservice.StatusLivraison;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponse {
    private Colis colis;
    private StatusLivraison statut;
    private Location location;
}

