package com.drone.entities;

import com.drone.enums.Model;
import com.drone.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Data
@Table(name = "drone")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    private int weightLimit;
    private int batteryLevel;
    private State state;
    private Model model;
    @OneToMany(mappedBy = "drone")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Medication> medications;

}
