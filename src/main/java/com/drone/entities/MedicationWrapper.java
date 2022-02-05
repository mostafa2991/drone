package com.drone.entities;

import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MedicationWrapper {

    List<Integer> medicationsId;

}
