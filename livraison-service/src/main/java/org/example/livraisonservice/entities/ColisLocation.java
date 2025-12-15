package org.example.livraisonservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColisLocation {
    private Long colisId;
    private double latitude;
    private double longitude;
    // destination finale
    private double destLatitude;
    private double destLongitude;
}

