package com.drone.service;

import com.drone.dto.DroneDto;
import com.drone.dto.ValidDroneDto;
import com.drone.entities.Drone;
import com.drone.enums.State;
import com.drone.exceptions.AlreadyExistException;
import com.drone.exceptions.FullDroneException;
import com.drone.exceptions.GenericExceptionResponse;
import com.drone.exceptions.NotFoundException;
import com.drone.mapper.DroneMapper;
import com.drone.mapper.ValidDroneMapper;
import com.drone.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DroneServiceImpl implements DroneService {

    //constants
    private static final int DRONE_MAX = 10;

    @Autowired
    private DroneRepository droneRepository;


    @Override
    public DroneDto addDrone(DroneDto droneDto) {
//        format brackets
        checkMaxDronesCountLimit(DRONE_MAX);
        checkDuplicates(droneDto);
        Drone drone = DroneMapper.INSTANCE.dtoToEntity(droneDto);
        drone = droneRepository.save(drone);
        DroneDto newDroneDto = DroneMapper.INSTANCE.entityToDto(drone);
        return newDroneDto;

    }

    @Override
    public ValidDroneDto getValidDroneForLoading(long medicationWeight , String DroneSerialNumber) {

        Drone drone = getDroneEntityBySerialNumber(DroneSerialNumber);
        if (drone.getBatteryLevel() < 25)
            throw new NotFoundException("Drone: " + DroneSerialNumber + " has low battery");
        if (drone.getState() != State.IDLE && drone.getState() != State.LOADING)
            throw new NotFoundException("Drone: " + DroneSerialNumber + " is not ready for loading items");
        if (drone.getWeightLimit() < medicationWeight)
            throw new NotFoundException("Drone: " + DroneSerialNumber + "can't load this medication over weight");
        ValidDroneDto validDrone = ValidDroneMapper.INSTANCE.entityToValidDto(drone);
        return validDrone;

    }

    @Override
    public DroneDto getDroneBySerialNumber(String serialNumber) {

        Drone drone = getDroneEntityBySerialNumber(serialNumber);
        DroneDto droneDto = DroneMapper.INSTANCE.entityToDto(drone);
        return droneDto;

    }



    private Drone getDroneEntityBySerialNumber(String serialNumber) {

        Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber)).
                orElseThrow(() -> new NotFoundException("can't find drone with serial number: " + serialNumber));
        return drone;

    }

    @Override
    public List<DroneDto> getAvailableDroneForLoading() {

        if (countDrones() == 0) throw new GenericExceptionResponse("No drones found");
        List<DroneDto> dronesDtos =
                Optional.ofNullable(droneRepository.getAvailableDroneForLoading())
                        .map(entities -> entities.stream()
                                .map(DroneMapper.INSTANCE::entityToDto)
                                .collect(Collectors.toList()))
                        .orElseThrow(() -> new NotFoundException("No drones Available for loading."));
        return dronesDtos;

    }

    private long countDrones() {

        return droneRepository.count();

    }

    private void checkMaxDronesCountLimit(int maxCount) {

        long count = countDrones();
        if (count == maxCount) throw new FullDroneException();

    }

    private boolean checkDuplicates(DroneDto droneDto) {

        Drone drone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        if (Objects.nonNull(drone))
            throw new GenericExceptionResponse("The serial number for drone is already exist:" + droneDto.getSerialNumber());
        return true;

    }


}
