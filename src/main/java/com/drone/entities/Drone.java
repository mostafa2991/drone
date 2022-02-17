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
    private Integer id;
    private String serialNumber;
    private Long weightLimit;
    private Integer batteryLevel;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Model model;
    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Medication> medications = new ArrayList<>();

}
