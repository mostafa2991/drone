package com.drone.controller;

import com.drone.dto.MedicationDto;
import com.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;


    @GetMapping("/{medicationId}")
    public ResponseEntity<MedicationDto> getMedication(@PathVariable String code) {
        return ResponseEntity.ok().body(this.medicationService.getMedicationByCode(code));
    }

    @PostMapping
    public ResponseEntity<MedicationDto> addMedication(@Valid @RequestBody MedicationDto medicationDto) {
        return ResponseEntity.ok(this.medicationService.addMedication(medicationDto));
    }

    @PostMapping("/assign/{medicationCode}/{DroneSerialNumber}")
    public ResponseEntity<MedicationDto> assignMedicationToDrone(@PathVariable String medicationCode, @PathVariable String droneSerialNumber) {
        return ResponseEntity.ok(this.medicationService.assignMedicationToDrone(medicationCode, droneSerialNumber));
    }

    @GetMapping("/drone/{droneId}")
    public ResponseEntity<List<MedicationDto>> getMedicationsByDroneSerialNumber(@PathVariable String droneSerialNumber) {
        return ResponseEntity.ok(this.medicationService.getMedicationsByDroneSerialNumber(droneSerialNumber));
    }

}

