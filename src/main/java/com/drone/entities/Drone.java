package com.drone.entities;

import com.drone.enums.Model;
import com.drone.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;


@Entity
@Data
@Table(name = "drone")
@Builder
@AllArgsConstructor
@ToString
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100, min = 3, message = "Please enter the drone serialNumber")
    @NotNull
    private String serialNumber;
    @Max(value = 500)
    @Min(value = 0)
    private int weightLimit;
    @Max(100)
    @Min(0)
    private int batteryLevel;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Model model;
    @OneToMany(mappedBy = "drone")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Medication> medications;

    public Drone() {
        this.weightLimit = 100;
        this.batteryLevel = 100;
        this.state = state.IDLE;
        this.model = model.Lightweight;
    }
}
