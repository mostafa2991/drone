package com.drone.service;

import com.drone.dto.MedicationDto;
import com.drone.entities.Medication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicationService {

    ResponseEntity<Object> addMedication(MedicationDto medicationDto);
    Medication getMedicationById(long  id);
    List<Medication> getAllMedication();
    ResponseEntity<Object> assignMedicationToDrone(Long medicationId,Long droneId);
    ResponseEntity<Object> getMedicationByDroneId(Long droneId);
}
