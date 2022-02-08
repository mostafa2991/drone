package com.drone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


@Entity
@Data
@Table(name = "medication")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private String code;
    private String imageUrl;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "drone_id")
    private Drone drone;
}
