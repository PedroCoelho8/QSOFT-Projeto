package com.isep.g200.service.mapper;

import com.isep.g200.domain.Specialty;
import com.isep.g200.domain.Vet;
import com.isep.g200.service.dto.SpecialtyDTO;
import com.isep.g200.service.dto.VetDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vet} and its DTO {@link VetDTO}.
 */
@Mapper(componentModel = "spring")
public interface VetMapper extends EntityMapper<VetDTO, Vet> {
    @Mapping(target = "specialty", source = "specialty", qualifiedByName = "specialtyId")
    VetDTO toDto(Vet s);

    @Named("specialtyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SpecialtyDTO toDtoSpecialtyId(Specialty specialty);
}
