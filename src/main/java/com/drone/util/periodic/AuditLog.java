package com.drone.util.periodic;

import com.drone.entities.Drone;
import com.drone.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class AuditLog {

    @Autowired
    private DroneService droneService;

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {
        System.out.println("in here");
        List<Drone> drones= droneService.getDrones();
        for (Drone drone : drones){
            log.info("the battery level for drone:{} is:{}", drone.getId(),drone.getBatteryLevel());
        }
    }
}
