package com.drone.dto;

import com.drone.entities.Medication;
import com.drone.enums.Model;
import com.drone.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class DroneDto {

    private static final int SERIAL_NUMBER_MIN = 3;
    private static final int SERIAL_NUMBER_MAX = 100;
    private static final int WEIGHT_LIMIT_DEFAULT = 100;
    private static final int WEIGHT_LIMIT_MIN = 100;
    private static final int WEIGHT_LIMIT_MAX = 500;
    private static final int BATTERY_LEVEL_DEFAULT = 100;
    private static final int BATTERY_LEVEL_MIN = 100;
    private static final int BATTERY_LEVEL_MAX = 100;

    @Size(max = SERIAL_NUMBER_MAX, min = SERIAL_NUMBER_MIN)
    @NotNull
    @NotBlank(message = "not allow for empty serial")
    @Pattern(regexp = "^[\\S]+$", message = "not allow to have any space")
    private String serialNumber;
    @Max(value = WEIGHT_LIMIT_MAX)
    @Min(value = WEIGHT_LIMIT_MIN)
    private int weightLimit;
    @Max(BATTERY_LEVEL_MAX)
    @Min(BATTERY_LEVEL_MIN)
    private int batteryLevel;
//    @Enumerated(EnumType.STRING)
    private State state;
//    @Enumerated(EnumType.STRING)
    private Model model;
    @Null
    private Set<Medication> medications;

    public DroneDto() {
        this.weightLimit = WEIGHT_LIMIT_DEFAULT;
        this.batteryLevel = BATTERY_LEVEL_DEFAULT;
        this.state = state.IDLE;
        this.model = model.Lightweight;
    }
}
