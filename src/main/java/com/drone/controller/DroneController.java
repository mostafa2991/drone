package com.drone.controller;

import com.drone.dto.DroneDto;
import com.drone.dto.ValidDroneDto;
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

    @PostMapping("/addDrone")
    public ResponseEntity<DroneDto> addDrone(@Valid @RequestBody DroneDto droneDto) {

        return ResponseEntity.ok(this.droneService.addDrone(droneDto));

    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<DroneDto> getDroneBySerialNumber(@PathVariable String serial) {

        return ResponseEntity.ok().body(this.droneService.getDroneBySerialNumber(serial));

    }

    @GetMapping("/available")
    public ResponseEntity<List<DroneDto>> getAvailableDroneForLoading() {

        return ResponseEntity.ok(this.droneService.getAvailableDroneForLoading());
    }

    @GetMapping("/battery/{serial}")
    public ResponseEntity<Integer> getBatteryLevelByDroneSerialNumber(@PathVariable String serial) {

        return ResponseEntity.ok(this.droneService.getDroneBySerialNumber(serial).getBatteryLevel());

    }

    @GetMapping("/medicWeight/{medicWeight}/getValidDrone/{serial}")
    public ValidDroneDto getValidDroneForLoading(@PathVariable long medicWeight , @PathVariable String serial) {

        return droneService.getValidDroneForLoading(medicWeight ,serial);
    }
}

