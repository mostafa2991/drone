package com.drone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Data
@Table(name = "medication")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    @NotNull
    private String name;
    @Min(value = 10)
    @NotNull
    private int weight;
    @Pattern(regexp = "^[A-Z0-9_]+$")
    @NotNull
    private String code;
    private String imageUrl;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "drone_id")
    private Drone drone;
}
