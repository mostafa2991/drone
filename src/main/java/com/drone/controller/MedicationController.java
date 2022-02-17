package com.drone.controller;

import com.drone.dto.MedicationDto;
import com.drone.dto.ValidDroneDto;
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


    @GetMapping("/code/{medicationCode}")
    public ResponseEntity<MedicationDto> getMedicationByCode(@PathVariable String medicationCode) {

        return ResponseEntity.ok().body(this.medicationService.getMedicationByCode(medicationCode));

    }

    @PostMapping("/addMedication")
    public ResponseEntity<MedicationDto> addMedication(@Valid @RequestBody MedicationDto medicationDto) {

        return ResponseEntity.ok(this.medicationService.addMedication(medicationDto));

    }

    @PostMapping("/assign/code/{medicationCode}")
    public ResponseEntity<String> assignMedicationToDrone(@PathVariable String medicationCode, @RequestBody ValidDroneDto validDrone) {

        return ResponseEntity.ok(this.medicationService.assignMedicationToDrone(medicationCode, validDrone));

    }

    @GetMapping("getMedication/drone/{droneId}")
    public ResponseEntity<List<MedicationDto>> getMedicationsByDroneId(@PathVariable Integer droneId) {

        return ResponseEntity.ok(this.medicationService.getMedicationsByDroneId(droneId));

    }

}

