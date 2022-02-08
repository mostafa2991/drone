package com.drone.service;

import com.drone.dto.MedicationDto;
import com.drone.entities.Drone;
import com.drone.entities.Medication;
import com.drone.enums.State;
import com.drone.exceptions.AlreadyExistException;
import com.drone.exceptions.NotFoundException;
import com.drone.mapper.MedicationMapper;
import com.drone.repositories.DroneRepository;
import com.drone.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DroneRepository droneRepository;

    @Override
    public MedicationDto addMedication(MedicationDto medicationDto) {
        String code = medicationDto.getCode();
        Medication medication = medicationRepository.findByCode(code);
        if (Objects.nonNull(medication)) throw new AlreadyExistException(code);
        medication = MedicationMapper.INSTANCE.dtoToEntity(medicationDto);
        medication = medicationRepository.save(medication);
        MedicationDto newMedicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
        return newMedicationDto;
    }

    @Override
    public MedicationDto getMedicationByCode(String code) {
        Medication medication = Optional.ofNullable(medicationRepository.findByCode(code)).
                orElseThrow(() -> new NotFoundException("can't find medication with code: " + code));
        MedicationDto medicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
        return medicationDto;
    }

    @Override
    public List<MedicationDto> getMedicationsByDroneSerialNumber(String DroneSerialNumber) {
        Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(DroneSerialNumber)).
                orElseThrow(() -> new NotFoundException("can't find drone with serial number: " + DroneSerialNumber));
        Long droneId = drone.getId();
        List<MedicationDto> medicationDtos =
                Optional.ofNullable(medicationRepository.getMedicationsByDroneId(droneId))
                        .map(entities -> entities.stream()
                                .map(MedicationMapper.INSTANCE::entityToDto)
                                .collect(Collectors.toList()))
                        .orElseThrow(() -> new NotFoundException("No medication Available for drone: " + DroneSerialNumber));
        return medicationDtos;
    }
//fix not found exception
    @Override
    public MedicationDto assignMedicationToDrone(String medicationCode, String DroneSerialNumber) {
        Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(DroneSerialNumber)).
                orElseThrow(() -> new NotFoundException("can't find drone with serial number: " + DroneSerialNumber));
        if (drone.getBatteryLevel() < 25)
            throw new NotFoundException("Drone: " + DroneSerialNumber + " has low battery");
        if (drone.getState() != State.IDLE && drone.getState() != State.LOADING)
            throw new NotFoundException("Drone: " + DroneSerialNumber + " is not ready for loading items");
        int droneAvailableWeight = drone.getWeightLimit();

        Medication medication = Optional.ofNullable(medicationRepository.findByCode(medicationCode)).
                orElseThrow(() -> new NotFoundException("can't find medication with code: " + medicationCode));
        int medicationWeight = medication.getWeight();
        if (droneAvailableWeight < medicationWeight)
            throw new NotFoundException("Drone: " + DroneSerialNumber + "can't load medication " + medicationCode + " over weight");
        drone.setWeightLimit(droneAvailableWeight - medicationWeight);
        drone.setState(State.LOADING);
        medication.setDrone(drone);
        MedicationDto medicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
        return medicationDto;
    }
}

