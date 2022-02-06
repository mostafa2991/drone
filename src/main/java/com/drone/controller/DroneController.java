package com.drone.controller;

import com.drone.dto.DroneDto;
import com.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<Object> addDrone(@Valid @RequestBody DroneDto droneDto) {
        return droneService.addDrone(droneDto);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<Object> getDroneBySerialNumber(@PathVariable String serialNumber) {
        return droneService.getDrone(serialNumber);
    }

    @GetMapping("/available")
    public ResponseEntity<Object> getAvailableDroneForLoading() {
        return droneService.getAvailableDroneForLoading();
    }

    @GetMapping("/battery/{droneId}")
    public ResponseEntity<Object> getBatteryLevelByDroneId(@PathVariable Long droneId) {
        return droneService.getBatteryLevelByDroneId(droneId);
    }
}

