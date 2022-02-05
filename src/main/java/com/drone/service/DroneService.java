package com.drone.service;

import com.drone.entities.Drone;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {

    ResponseEntity<Object> addDrone(Drone drone);

    Drone getDroneById(Long droneId);
    ResponseEntity<Object> getDroneDtoById(Long droneId);
    List<Drone> getDrones();
    long countDrones();

    ResponseEntity<Object> getAvailableDroneForLoading();

    int getBatteryLevelByDroneId(Long droneId);

}
