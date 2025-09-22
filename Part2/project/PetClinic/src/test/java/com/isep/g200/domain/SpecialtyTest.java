package com.isep.g200.domain;

import static com.isep.g200.domain.SpecialtyTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.isep.g200.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SpecialtyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Specialty.class);
        Specialty specialty1 = getSpecialtySample1();
        Specialty specialty2 = new Specialty();
        assertThat(specialty1).isNotEqualTo(specialty2);

        specialty2.setId(specialty1.getId());
        assertThat(specialty1).isEqualTo(specialty2);

        specialty2 = getSpecialtySample2();
        assertThat(specialty1).isNotEqualTo(specialty2);
    }
}
