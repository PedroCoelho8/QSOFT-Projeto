package com.isep.g200.service.mapper;

import com.isep.g200.domain.Pet;
import com.isep.g200.domain.Visit;
import com.isep.g200.service.dto.PetDTO;
import com.isep.g200.service.dto.VisitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Visit} and its DTO {@link VisitDTO}.
 */
@Mapper(componentModel = "spring")
public interface VisitMapper extends EntityMapper<VisitDTO, Visit> {
    @Mapping(target = "pet", source = "pet", qualifiedByName = "petId")
    VisitDTO toDto(Visit s);

    @Named("petId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PetDTO toDtoPetId(Pet pet);
}
