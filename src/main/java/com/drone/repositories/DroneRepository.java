package com.drone.repositories;

import com.drone.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query(value = "SELECT * FROM DRONE WHERE STATE In ('IDLE','LOADING')", nativeQuery = true)
    List<Drone> getAvailableDroneForLoading();

    Drone findBySerialNumber(String serialNumber);

}
