package com.drone.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto {

    private Long id;
    private String name;
    private int weight;
    private String code;
    private String imageUrl;
}
