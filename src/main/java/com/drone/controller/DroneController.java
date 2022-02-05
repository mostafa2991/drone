package com.drone.controller;

import com.drone.entities.Drone;
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

    @GetMapping("/{droneId}")
    public ResponseEntity<Object> getDrone(@PathVariable Long droneId) {
        return droneService.getDroneDtoById(droneId);
    }

    @PostMapping
    public ResponseEntity<Object> addDrone(@Valid @RequestBody Drone drone) {
        return droneService.addDrone(drone);
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

