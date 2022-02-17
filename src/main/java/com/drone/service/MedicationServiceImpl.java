package com.drone.service;

import com.drone.dto.MedicationDto;
import com.drone.dto.ValidDroneDto;
import com.drone.entities.Medication;
import com.drone.enums.State;
import com.drone.exceptions.AlreadyExistException;
import com.drone.exceptions.GenericExceptionResponse;
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

    //spag
    @Override
    public MedicationDto addMedication(MedicationDto medicationDto) {

        checkDuplicates(medicationDto);
        Medication medication = MedicationMapper.INSTANCE.dtoToEntity(medicationDto);
        medication = medicationRepository.save(medication);
        MedicationDto newMedicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
        return newMedicationDto;

    }

    //repeated
    @Override
    public MedicationDto getMedicationByCode(String code) {

        Medication medication = getMedicationEntityByCode(code);
        MedicationDto medicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
        return medicationDto;

    }


    private Medication getMedicationEntityByCode(String code) {

        if (countMedication() == 0) throw new GenericExceptionResponse("No medications found");
        Medication medication = Optional.ofNullable(medicationRepository.findByCode(code)).
                orElseThrow(() -> new NotFoundException("can't find medication with code: " + code));
        return medication;

    }

    @Override
    public List<MedicationDto> getMedicationsByDroneId(Integer droneId) {

        if (countMedication() == 0) throw new GenericExceptionResponse("No medications found");
        ;
        List<MedicationDto> medicationDtos =
                Optional.ofNullable(medicationRepository.getMedicationsByDroneId(droneId))
                        .map(entities -> entities.stream()
                                .map(MedicationMapper.INSTANCE::entityToDto)
                                .collect(Collectors.toList()))
                        .orElseThrow(() -> new NotFoundException("No medications Available for this drone"));
        return medicationDtos;

    }

    @Override
    public String assignMedicationToDrone(String medicationCode, ValidDroneDto validDrone) {

        Medication medication = getMedicationEntityByCode(medicationCode);
        long medicationWeight = medication.getWeight();
        long droneAvailableWeight = validDrone.getWeightLimit();
        droneAvailableWeight = droneAvailableWeight - medicationWeight;
        String state = State.LOADING.toString();
        if(droneAvailableWeight == 0 ) state = State.LOADED.toString();
        medicationRepository.updateAssignedMediction( validDrone.getId(), medication.getId());
        medicationRepository.updateAssignedDrone(state, droneAvailableWeight, validDrone.getId());
        return "Medication has been assigns correctly";

    }

    private boolean checkDuplicates(MedicationDto medicationDto) {

        String code = medicationDto.getCode();
        Medication medication = medicationRepository.findByCode(code);
        if (Objects.nonNull(medication)) throw new AlreadyExistException(code);
        return true;

    }

    private long countMedication() {

        return medicationRepository.count();

    }


}

