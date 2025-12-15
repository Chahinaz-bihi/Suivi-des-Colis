package org.example.colisservice.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColisStats {
    private long enCours;
    private long delivered;
    private long outForDelivery;
    private double poidsTotal;
}
