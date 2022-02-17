package com.drone.service;

import com.drone.dto.MedicationDto;
import com.drone.dto.ValidDroneDto;
import com.drone.entities.Drone;
import com.drone.entities.Medication;

import java.util.List;

public interface MedicationService {

    MedicationDto addMedication(MedicationDto medicationDto);
    MedicationDto getMedicationByCode(String code);
    String assignMedicationToDrone(String medicationCode, ValidDroneDto validDrone);
    List<MedicationDto> getMedicationsByDroneId(Integer droneId);
}
