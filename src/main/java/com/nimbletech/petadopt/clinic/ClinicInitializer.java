package com.nimbletech.petadopt.clinic;

import com.nimbletech.petadopt.clinic.domain.ClinicRepository;
import org.springframework.modulith.ApplicationModuleInitializer;
import org.springframework.stereotype.Component;

@Component
public class ClinicInitializer implements ApplicationModuleInitializer {
    private final ClinicRepository clinicRepository;

    public ClinicInitializer(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public void initialize() {
        if (clinicRepository.count() == 0) {
            Clinic clinic1 = new Clinic();
            clinic1.setName("Downtown Pet Clinic");
            clinic1.setAddress("123 Main St, Cityville");
            clinic1.setPhoneNumber("123-456-7890");
            clinic1.setLatitude(40.712776);
            clinic1.setLongitude(-74.005974);
            clinicRepository.save(clinic1);

            Clinic clinic2 = new Clinic();
            clinic2.setName("Uptown Animal Hospital");
            clinic2.setAddress("789 Broadway, Metropolis");
            clinic2.setPhoneNumber("987-654-3210");
            clinic2.setLatitude(40.730610);
            clinic2.setLongitude(-73.935242);
            clinicRepository.save(clinic2);
        }
    }
}
