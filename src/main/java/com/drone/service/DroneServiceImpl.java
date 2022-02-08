package com.drone.service;

import com.drone.dto.DroneDto;
import com.drone.entities.Drone;
import com.drone.exceptions.AlreadyExistException;
import com.drone.exceptions.FullDroneException;
import com.drone.exceptions.NotFoundException;
import com.drone.mapper.DroneMapper;
import com.drone.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DroneServiceImpl implements DroneService {

    private static final int DRONE_MAX = 10;

    @Autowired
    private DroneRepository droneRepository;


    @Override
    public DroneDto addDrone(DroneDto droneDto) {
        if (countDrones() == DRONE_MAX) throw new FullDroneException();
        Drone drone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        if (Objects.nonNull(drone)) throw new AlreadyExistException(droneDto.getSerialNumber());
        drone = DroneMapper.INSTANCE.dtoToEntity(droneDto);
        drone = droneRepository.save(drone);
        DroneDto newDroneDto = DroneMapper.INSTANCE.entityToDto(drone);
        return newDroneDto;
    }

    @Override
    public DroneDto getDroneBySerialNumber(String serialNumber) {
        Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber)).
                orElseThrow(() -> new NotFoundException("can't find drone with serial number: " + serialNumber));
        DroneDto droneDto = DroneMapper.INSTANCE.entityToDto(drone);
        return droneDto;
    }

    @Override
    public long countDrones() {
        Long registeredDrones = droneRepository.count();
        return registeredDrones;
    }

    @Override
    public List<DroneDto> getAvailableDroneForLoading() {

        List<DroneDto> dronesDtos =
                Optional.ofNullable(droneRepository.getAvailableDroneForLoading())
                        .map(entities -> entities.stream()
                                .map(DroneMapper.INSTANCE::entityToDto)
                                .collect(Collectors.toList()))
                        .orElseThrow(() -> new NotFoundException("No drones Available for loading."));

        return dronesDtos;
    }


}
