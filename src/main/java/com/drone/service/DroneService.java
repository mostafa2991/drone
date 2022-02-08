package com.drone.service;

import com.drone.dto.DroneDto;

import java.util.List;

public interface DroneService {

    long countDrones();
    DroneDto getDroneBySerialNumber(String serialNumber);
    DroneDto addDrone(DroneDto droneDto);
    List<DroneDto> getAvailableDroneForLoading();

}
