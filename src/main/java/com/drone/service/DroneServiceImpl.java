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

import java.util.ArrayList;
import java.util.List;


@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public ResponseEntity<Object> addDrone(DroneDto droneDto) {
        Drone drone = DroneMapper.INSTANCE.dtoToEntity(droneDto);
        Long registeredDrones = countDrones();
        if (registeredDrones < 10) {
            try {
                Drone newDrone = droneRepository.save(drone);
                DroneDto newDroneDto = DroneMapper.INSTANCE.entityToDto(newDrone);
                return ResponseHandler.generateResponse("Successfully registered new drone!", HttpStatus.OK, droneDto);
            } catch (Exception e) {
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
            }
        } else {
            return ResponseHandler.generateResponse("Can't registered new drone wait for one to return!", HttpStatus.FORBIDDEN, null);
        }
    }

    @Override
    public Drone getDroneById(Long id) {
        return droneRepository.getById(id);
    }

    @Override
    public ResponseEntity<Object> getDroneDtoById(Long droneId) {
        Drone getDrone = getDroneById(droneId);
        DroneDto droneDto = DroneMapper.INSTANCE.entityToDto(getDrone);
        return ResponseHandler.generateResponse("getDrone", HttpStatus.OK, droneDto);
    }

    @Override
    public List<Drone> getDrones() {
        return droneRepository.findAll();
    }

    @Override
    public long countDrones() {
        Long registeredDrones = droneRepository.count();
        return registeredDrones;
    }

    @Override
    public ResponseEntity<Object> getAvailableDroneForLoading() {
        List<Drone> drones= droneRepository.getAvailableDroneForLoading();
        if(drones==null){
            return ResponseHandler.generateResponse("No drone available for loading!", HttpStatus.NOT_FOUND, null);
        }
        else{
            List<DroneDto> dronesDto = new ArrayList<>();
            for(Drone drone : drones){
                DroneDto droneDto = DroneMapper.INSTANCE.entityToDto(drone);
                dronesDto.add(droneDto);
            }
            return ResponseHandler.generateResponse("The available drones for loading: ", HttpStatus.OK, dronesDto);
        }
    }

    @Override
    public int getBatteryLevelByDroneId(Long droneId) {
        return droneRepository.getBatteryLevelByDroneId(droneId);
    }

}
