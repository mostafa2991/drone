package com.drone.controller;

import com.drone.dto.MedicationDto;
import com.drone.entities.Medication;
import com.drone.mapper.MedicationMapper;
import com.drone.service.MedicationService;
import com.drone.util.api.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;


    @GetMapping("/{medicationId}")
    public ResponseEntity<Object> getMedication(@PathVariable Long medicationId) {
        Medication getMedication = medicationService.getMedicationById(medicationId);
        MedicationDto medicationDto = MedicationMapper.INSTANCE.entityToDto(getMedication);
        return ResponseHandler.generateResponse("getMedication", HttpStatus.OK, medicationDto);
    }

    @PostMapping
    public ResponseEntity<Object> createMedication(@Valid @RequestBody Medication medication) {
        return medicationService.addMedication(medication);
    }

    @PostMapping("/assign/{medicationId}/{droneId}")
    public ResponseEntity<Object> assignMedicationToDrone(@PathVariable Long medicationId, @PathVariable Long droneId) {
        return medicationService.assignMedicationToDrone(medicationId, droneId);
    }
    @GetMapping("/drone/{droneId}")
    public ResponseEntity<Object> getMedicationByDroneId(@PathVariable Long droneId) {
        return medicationService.getMedicationByDroneId(droneId);
    }

}

