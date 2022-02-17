package com.drone.service;

import com.drone.dto.DroneDto;
import com.drone.dto.ValidDroneDto;
import com.drone.entities.Drone;

import java.util.List;

public interface DroneService {

    DroneDto getDroneBySerialNumber(String serialNumber);
    DroneDto addDrone(DroneDto droneDto);
    ValidDroneDto getValidDroneForLoading(long medicationWeight , String DroneSerialNumber);
    List<DroneDto> getAvailableDroneForLoading();

}
