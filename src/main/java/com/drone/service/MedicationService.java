package com.drone.service;

import com.drone.entities.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicationService {

    ResponseEntity<Object> addMedication(Medication medication);
    Medication getMedicationById(long  id);
    List<Medication> getAllMedication();
    ResponseEntity<Object> assignMedicationToDrone(Long medicationId,Long droneId);
    ResponseEntity<Object> getMedicationByDroneId(Long droneId);
}
