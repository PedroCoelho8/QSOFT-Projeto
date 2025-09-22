package com.isep.g200.service.mapper;

import com.isep.g200.domain.Specialty;
import com.isep.g200.service.dto.SpecialtyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Specialty} and its DTO {@link SpecialtyDTO}.
 */
@Mapper(componentModel = "spring")
public interface SpecialtyMapper extends EntityMapper<SpecialtyDTO, Specialty> {}
