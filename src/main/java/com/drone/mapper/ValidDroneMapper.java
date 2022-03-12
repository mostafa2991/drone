package com.drone.mapper;

import com.drone.dto.ValidDroneDto;
import com.drone.entities.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ValidDroneMapper {

    ValidDroneMapper INSTANCE = Mappers.getMapper(ValidDroneMapper.class);

    ValidDroneDto entityToValidDto(Drone drone);
    Drone validDtoToEntity(ValidDroneDto validDroneDto);
}
