package com.drone.service;

import com.drone.dto.MedicationDto;
import com.drone.entities.Drone;
import com.drone.entities.Medication;
import com.drone.enums.State;
import com.drone.mapper.MedicationMapper;
import com.drone.repositories.MedicationRepository;
import com.drone.util.api.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DroneService droneService;

    @Override
    public ResponseEntity<Object> addMedication(Medication medication) {
        try {
            Medication newMedication = medicationRepository.save(medication);
            return ResponseHandler.generateResponse("Successfully created new medication!", HttpStatus.OK, newMedication);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @Override
    public Medication getMedicationById(long id) {
        return medicationRepository.getById(id);
    }

    @Override
    public List<Medication> getAllMedication() {
        return medicationRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> assignMedicationToDrone(Long medicationId, Long droneId) {
        Drone getDrone = droneService.getDroneById(droneId);
        if(getDrone.getBatteryLevel()<25 ) {
            return ResponseHandler.generateResponse("Drone: "+getDrone.getId().toString()+" can't load battery < 25", HttpStatus.OK, null);
        }
        if(getDrone.getState()!=State.IDLE && getDrone.getState()!=State.LOADING  ) {
            return ResponseHandler.generateResponse("Drone: "+getDrone.getId().toString()+" is not ready to load items", HttpStatus.OK, null);
        }
        int droneAvailableWeight = getDrone.getWeightLimit();
        Medication getMedication = getMedicationById(medicationId);
        int medicationWeight = getMedication.getWeight();
        if (droneAvailableWeight < medicationWeight) {
            return ResponseHandler.generateResponse("Medication: " + getMedication.getId().toString() +
                    " Can't be loaded in drone: " + getDrone.getSerialNumber().toString() + " over weight", HttpStatus.OK, null);
        } else {
            getDrone.setWeightLimit(droneAvailableWeight-medicationWeight);
            getDrone.setState(State.LOADING);
            getMedication.setDrone(getDrone);
            addMedication(getMedication);
            if(getDrone.getWeightLimit()==0) getDrone.setState(State.LOADED);
            return ResponseHandler.generateResponse("Medication: " + getMedication.getId().toString() +
                    " Set correctly with Drone: " + getDrone.getSerialNumber().toString(), HttpStatus.OK, null);
        }


    }

    @Override
    public ResponseEntity<Object> getMedicationByDroneId(Long droneId) {
        List<Medication> medications= medicationRepository.getMedicationByDroneId(droneId);
        if(medications==null){
            return ResponseHandler.generateResponse("No medication available for drone: " +droneId, HttpStatus.NOT_FOUND, null);
        }
        List<MedicationDto> medicationsDto = new ArrayList<>();
        for(Medication medication :medications){
            MedicationDto medicationDto = MedicationMapper.INSTANCE.entityToDto(medication);
            medicationsDto.add(medicationDto);
        }
        return ResponseHandler.generateResponse("The available medication for drone: " +droneId +" are:", HttpStatus.OK, medicationsDto);
    }


}
