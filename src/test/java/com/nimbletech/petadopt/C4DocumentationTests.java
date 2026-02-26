package com.nimbletech.petadopt;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class C4DocumentationTests {

    private final ApplicationModules modules = 
        ApplicationModules.of(PetAdoptApplication.class);

    @Test
    void generateC4Documentation() {
        new Documenter(modules)
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml()
            .writeAggregatingDocument();
    }

}
