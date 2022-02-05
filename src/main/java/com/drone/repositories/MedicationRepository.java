package com.drone.repositories;

import com.drone.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query(value = "SELECT * FROM Medication m WHERE m.Drone_ID = ?1", nativeQuery = true)
    List<Medication> getMedicationByDroneId(Long droneId);
}
