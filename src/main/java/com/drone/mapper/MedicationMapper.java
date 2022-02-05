package com.drone.mapper;

import com.drone.dto.MedicationDto;
import com.drone.entities.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicationMapper {

    MedicationMapper INSTANCE = Mappers.getMapper(MedicationMapper.class);

    MedicationDto entityToDto(Medication medication);
}
