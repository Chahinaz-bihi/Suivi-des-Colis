package org.example.locationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NominatimResponse {
    private String lat;
    private String lon;
    private String display_name;
}
