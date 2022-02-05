package com.drone.mapper;

import com.drone.dto.DroneDto;
import com.drone.entities.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DroneMapper {

    DroneMapper INSTANCE = Mappers.getMapper(DroneMapper.class);

    DroneDto entityToDto(Drone drone);
    Drone DtoToEntity(DroneDto droneDto);
}
