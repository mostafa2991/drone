package com.drone.controller;

import com.drone.dto.DroneDto;
import com.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<DroneDto> addDrone(@Valid @RequestBody DroneDto droneDto) {
        return ResponseEntity.ok(this.droneService.addDrone(droneDto));
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<DroneDto> getDroneBySerialNumber(@PathVariable String serialNumber) {
        return ResponseEntity.ok().body(this.droneService.getDroneBySerialNumber(serialNumber));
    }

    @GetMapping("/available")
    public ResponseEntity<List<DroneDto>> getAvailableDroneForLoading() {
        return ResponseEntity.ok(this.droneService.getAvailableDroneForLoading());
    }

    @GetMapping("/battery/{serialNumber}")
    public ResponseEntity<Integer> getBatteryLevelByDroneSerialNumber(@PathVariable String serialNumber) {
        return ResponseEntity.ok(this.droneService.getDroneBySerialNumber(serialNumber).getBatteryLevel());
    }
}

