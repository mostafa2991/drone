package com.drone.dto;

import com.drone.enums.Model;
import com.drone.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Builder
public class DroneDto {

    private static final int SERIAL_NUMBER_MIN = 3;
    private static final int SERIAL_NUMBER_MAX = 100;
    private static final long WEIGHT_LIMIT_DEFAULT = 100L;
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
    private Long weightLimit;
    @Max(BATTERY_LEVEL_MAX)
    @Min(BATTERY_LEVEL_MIN)
    private Integer batteryLevel;
    private State state;
    private Model model;

    public DroneDto() {
        this.weightLimit = WEIGHT_LIMIT_DEFAULT;
        this.batteryLevel = BATTERY_LEVEL_DEFAULT;
        this.state = state.IDLE;
        this.model = model.Lightweight;
    }
}
