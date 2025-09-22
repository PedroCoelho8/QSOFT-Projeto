package com.isep.g200.domain;

import static com.isep.g200.domain.SpecialtyTestSamples.*;
import static com.isep.g200.domain.VetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.isep.g200.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vet.class);
        Vet vet1 = getVetSample1();
        Vet vet2 = new Vet();
        assertThat(vet1).isNotEqualTo(vet2);

        vet2.setId(vet1.getId());
        assertThat(vet1).isEqualTo(vet2);

        vet2 = getVetSample2();
        assertThat(vet1).isNotEqualTo(vet2);
    }

    @Test
    void specialtyTest() {
        Vet vet = getVetRandomSampleGenerator();
        Specialty specialtyBack = getSpecialtyRandomSampleGenerator();

        vet.setSpecialty(specialtyBack);
        assertThat(vet.getSpecialty()).isEqualTo(specialtyBack);

        vet.specialty(null);
        assertThat(vet.getSpecialty()).isNull();
    }
}
