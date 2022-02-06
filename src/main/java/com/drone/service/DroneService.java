package com.drone.service;

import com.drone.dto.DroneDto;
import com.drone.entities.Drone;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {

    long countDrones();
    Drone getDroneBySerialNumber(String serialNumber);
    ResponseEntity<Object> getDrone(String serialNumber);
    ResponseEntity<Object> addDrone(DroneDto droneDto);
    ResponseEntity<Object> getAvailableDroneForLoading();
    ResponseEntity<Object> getBatteryLevelByDroneId(Long droneId);

}
