package com.drone.util;

import com.drone.entities.Medication;

import java.util.List;

public class CheckWeight {

    public static int getMedicationsWeight(List<Medication> medications) {
        int weight = 0;
        for (Medication medication : medications){
            weight+=medication.getWeight();
        }
        return weight;
    }
}
