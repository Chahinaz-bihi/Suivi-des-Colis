package org.example.colisservice.entities;
import jakarta.persistence.*;
import lombok.*;
import org.example.livraisonservice.StatusLivraison;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;


    // Ou si tu veux garder les deux :
    private String expediteur;       // Ex: "Sara", "Mohammed"

    private String destinataire;     // Ex: "Marrakech", "Fès"

    private double poids;

    @Enumerated(EnumType.STRING)
    private StatusLivraison statut;
}
