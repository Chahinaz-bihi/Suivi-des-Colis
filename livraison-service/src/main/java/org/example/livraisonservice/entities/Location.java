package org.example.livraisonservice.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String city;
    private double latitude;
    private double longitude;


}

