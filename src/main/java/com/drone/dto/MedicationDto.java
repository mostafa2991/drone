package com.drone.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {

    private static final int NAME_MIN = 3;
    private static final int NAME_MAX = 20;
    private static final int WEIGHT_MIN = 10;

    @Size(max = NAME_MAX, min = NAME_MIN)
    @NotNull
    @NotBlank(message = "not allow for empty name")
    @Pattern.List({
            @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "only have _- with numbers & letters"),
            @Pattern(regexp = "^[\\S]+$", message = "not allow to have any space")
    })
    private String name;
    @Min(value = WEIGHT_MIN)
    @NotNull
    private int weight;
    @Pattern.List({
            @Pattern(regexp = "^[A-Z0-9_]+$", message = "only have _ with numbers & letters"),
            @Pattern(regexp = "^[\\S]+$", message = "not allow to have any space")
    })
    @NotNull
    private String code;
    private String imageUrl;
    private DroneDto droneDto;
}
