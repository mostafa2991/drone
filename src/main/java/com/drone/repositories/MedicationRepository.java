package com.drone.repositories;

import com.drone.entities.Drone;
import com.drone.entities.Medication;
import com.drone.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query(value = "SELECT * FROM Medication m WHERE m.Drone_ID = ?1", nativeQuery = true)
    List<Medication> getMedicationsByDroneId(Integer droneId);

    Medication findByCode(String Code);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Medication m set m.Drone_ID = ?1 where m.id = ?2", nativeQuery = true)
    int updateAssignedMediction(Integer droneId , Integer medicationId );

    @Transactional
    @Modifying
    @Query(value = "UPDATE Drone d SET d.STATE = ?1 , d.WEIGHT_LIMIT = ?2  WHERE d.ID = ?3", nativeQuery = true)
    int updateAssignedDrone(String state, Long droneAvailableWeight, Integer droneId);



}
