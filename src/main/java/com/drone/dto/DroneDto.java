package com.drone.dto;

import com.drone.entities.Medication;
import com.drone.enums.Model;
import com.drone.enums.State;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneDto {

    private Long id;
    private String serialNumber;
    private int weightLimit;
    private int batteryLevel;
    private State state;
    private Model model;
    private Set<Medication> medications;
}
