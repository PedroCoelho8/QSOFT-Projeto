package com.isep.g200.service.mapper;

import com.isep.g200.domain.Owner;
import com.isep.g200.domain.Pet;
import com.isep.g200.domain.PetType;
import com.isep.g200.service.dto.OwnerDTO;
import com.isep.g200.service.dto.PetDTO;
import com.isep.g200.service.dto.PetTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pet} and its DTO {@link PetDTO}.
 */
@Mapper(componentModel = "spring")
public interface PetMapper extends EntityMapper<PetDTO, Pet> {
    @Mapping(target = "owner", source = "owner", qualifiedByName = "ownerId")
    @Mapping(target = "petType", source = "petType", qualifiedByName = "petTypeId")
    PetDTO toDto(Pet s);

    @Named("ownerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OwnerDTO toDtoOwnerId(Owner owner);

    @Named("petTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PetTypeDTO toDtoPetTypeId(PetType petType);
}
