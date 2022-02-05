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
    @GetMapping("/{droneId}")
    public ResponseEntity<Object> getDrone(@PathVariable Long droneId) {
        return droneService.getDroneDtoById(droneId);
    }
    @GetMapping("/available")
    public ResponseEntity<Object> getAvailableDroneForLoading() {
        return droneService.getAvailableDroneForLoading();
    }
    @GetMapping("/battery/{droneId}")
    public int getBatteryLevelByDroneId(@PathVariable Long droneId) {
        return droneService.getBatteryLevelByDroneId(droneId);
    }
}

