package com.drone.dto;

import com.drone.enums.Model;
import com.drone.enums.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DroneDtoTest {

    @Test
    public void testCreateMultiDroneDtoObjectWithDefaultConstructor() {
        DroneDto droneDto1 = new DroneDto();
        droneDto1.setSerialNumber("Drone1");
        droneDto1.setWeightLimit(100);
        droneDto1.setState(State.IDLE);
        droneDto1.setModel(Model.Lightweight);
        DroneDto droneDto2 = new DroneDto();
        droneDto1.setSerialNumber("Drone2");
        droneDto1.setBatteryLevel(100);
        DroneDto droneDto3 = new DroneDto();
        droneDto1.setSerialNumber("Drone3");

        assertEquals(droneDto1.getBatteryLevel(), droneDto2.getBatteryLevel(), droneDto3.getBatteryLevel());
        assertEquals(droneDto1.getState().toString(), droneDto2.getState().toString(), droneDto3.getState().toString());
        assertEquals(droneDto1.getModel().toString(), droneDto2.getModel().toString(), droneDto3.getModel().toString());
        assertEquals(droneDto1.getWeightLimit(), droneDto2.getWeightLimit(), droneDto3.getWeightLimit());
    }

    @Test
    public void testEmptyDroneDtoSerialNumber() {
        DroneDto droneDto1 = new DroneDto();
        droneDto1.setSerialNumber("Drone 1");
        DroneDto droneDto2 = new DroneDto();
        droneDto1.setSerialNumber("    ");
//        assertEquals(droneDto1.getSerialNumber(), "");
        assertEquals(droneDto2.getSerialNumber(), null);
        System.out.println((droneDto1));
    }
}
