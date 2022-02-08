package com.drone.service;

import com.drone.dto.MedicationDto;

import java.util.List;

public interface MedicationService {

    MedicationDto addMedication(MedicationDto medicationDto);
    MedicationDto getMedicationByCode(String code);
    MedicationDto assignMedicationToDrone(String medicationCode,String droneSerialNumber);
    List<MedicationDto> getMedicationsByDroneSerialNumber(String droneSerialNumber);
}
