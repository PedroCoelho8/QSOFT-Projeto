package com.isep.g200.domain;

import static com.isep.g200.domain.OwnerTestSamples.*;
import static com.isep.g200.domain.PetTestSamples.*;
import static com.isep.g200.domain.PetTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.isep.g200.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pet.class);
        Pet pet1 = getPetSample1();
        Pet pet2 = new Pet();
        assertThat(pet1).isNotEqualTo(pet2);

        pet2.setId(pet1.getId());
        assertThat(pet1).isEqualTo(pet2);

        pet2 = getPetSample2();
        assertThat(pet1).isNotEqualTo(pet2);
    }

    @Test
    void ownerTest() {
        Pet pet = getPetRandomSampleGenerator();
        Owner ownerBack = getOwnerRandomSampleGenerator();

        pet.setOwner(ownerBack);
        assertThat(pet.getOwner()).isEqualTo(ownerBack);

        pet.owner(null);
        assertThat(pet.getOwner()).isNull();
    }

    @Test
    void petTypeTest() {
        Pet pet = getPetRandomSampleGenerator();
        PetType petTypeBack = getPetTypeRandomSampleGenerator();

        pet.setPetType(petTypeBack);
        assertThat(pet.getPetType()).isEqualTo(petTypeBack);

        pet.petType(null);
        assertThat(pet.getPetType()).isNull();
    }
}
