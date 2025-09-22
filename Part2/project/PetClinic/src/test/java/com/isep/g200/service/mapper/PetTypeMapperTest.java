package com.isep.g200.service.mapper;

import static com.isep.g200.domain.PetTypeAsserts.*;
import static com.isep.g200.domain.PetTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetTypeMapperTest {

    private PetTypeMapper petTypeMapper;

    @BeforeEach
    void setUp() {
        petTypeMapper = new PetTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPetTypeSample1();
        var actual = petTypeMapper.toEntity(petTypeMapper.toDto(expected));
        assertPetTypeAllPropertiesEquals(expected, actual);
    }
}
