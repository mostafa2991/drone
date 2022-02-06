package com.drone.service;

import com.drone.dto.DroneDto;
import com.drone.entities.Drone;
import com.drone.mapper.DroneMapper;
import com.drone.repositories.DroneRepository;
import com.drone.util.api.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DroneServiceImpl implements DroneService {

    private static final int DRONE_MAX = 10;

    @Autowired
    private DroneRepository droneRepository;


    @Override
    public ResponseEntity<Object> addDrone(DroneDto droneDto) {
        if (countDrones() == DRONE_MAX)
            return ResponseHandler.generateResponse("Can't registered more drones already 10 registered", HttpStatus.FORBIDDEN, "No Data");
        Drone drone= getDroneBySerialNumber(droneDto.getSerialNumber());
        if(drone!=null) return ResponseHandler.generateResponse("This drone already registered!", HttpStatus.FORBIDDEN, "No Drone has been saved");
        drone = DroneMapper.INSTANCE.dtoToEntity(droneDto);
        try {
            drone = droneRepository.save(drone);
            DroneDto newDroneDto = DroneMapper.INSTANCE.entityToDto(drone);
            return ResponseHandler.generateResponse("Successfully registered new drone!", HttpStatus.OK, droneDto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.CONFLICT, "No Drone has been saved");
        }
    }

    @Override
    public Drone getDroneBySerialNumber(String serialNumber) {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return drone;
    }

    @Override
    public ResponseEntity<Object> getDrone(String serialNumber) {
        Drone drone = getDroneBySerialNumber(serialNumber);
        if (drone == null)
            return ResponseHandler.generateResponse("No drone available with serial number: "+serialNumber, HttpStatus.FORBIDDEN, "No Data");
        DroneDto droneDto = DroneMapper.INSTANCE.entityToDto(drone);
        return ResponseHandler.generateResponse("getDrone", HttpStatus.OK, droneDto);
    }

    @Override
    public long countDrones() {
        Long registeredDrones = droneRepository.count();
        return registeredDrones;
    }

    @Override
    public ResponseEntity<Object> getAvailableDroneForLoading() {

        List<DroneDto> dronesDto =
                Optional.ofNullable(droneRepository.getAvailableDroneForLoading())
                        .map(entities -> entities.stream()
                                .map(DroneMapper.INSTANCE::entityToDto)
                                .collect(Collectors.toList()))
                        .orElse(Collections.emptyList());

        if (dronesDto == null)
            return ResponseHandler.generateResponse("No drone available for loading!", HttpStatus.NOT_FOUND, "No Data");
        return ResponseHandler.generateResponse("The available drones for loading: ", HttpStatus.OK, dronesDto);
    }

    @Override
    public ResponseEntity<Object> getBatteryLevelByDroneId(Long droneId) {
        Drone drone = droneRepository.getBatteryLevelByDroneId(droneId);
        if (drone == null)
            return ResponseHandler.generateResponse("No drone with ID: " + droneId, HttpStatus.NOT_FOUND, "No Data");
        return ResponseHandler.generateResponse("The battery level for  drone with ID:", HttpStatus.OK, drone.getBatteryLevel());
    }


}
