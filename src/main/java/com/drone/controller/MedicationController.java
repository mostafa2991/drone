package com.drone.controller;

import com.drone.dto.MedicationDto;
import com.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return medicationService.getMedicationDtoById(medicationId);
    }

    @PostMapping
    public ResponseEntity<Object> createMedication(@Valid @RequestBody MedicationDto medicationDto) {
        return medicationService.addMedication(medicationDto);
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

