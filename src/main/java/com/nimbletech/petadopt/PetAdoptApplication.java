package com.nimbletech.petadopt;

import com.nimbletech.petadopt.appointment.model.Appointment;
import com.nimbletech.petadopt.appointment.model.AppointmentReason;
import com.nimbletech.petadopt.appointment.model.AppointmentStatus;
import com.nimbletech.petadopt.appointment.repository.AppointmentRepository;
import com.nimbletech.petadopt.clinic.model.Clinic;
import com.nimbletech.petadopt.clinic.repository.ClinicRepository;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import com.nimbletech.petadopt.user.model.Role;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class PetAdoptApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(BCryptPasswordEncoder passwordEncoder,
                                        PetRepository petRepository,
                                        UserRepository userRepository,
                                        AppointmentRepository appointmentRepository,
                                        ClinicRepository clinicRepository) {
        return args -> {
            boolean emptyDatabase = petRepository.count() == 0 && userRepository.count() == 0 && appointmentRepository.count() == 0;
            if (emptyDatabase) {
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


                String password = passwordEncoder.encode("test123");
                User user1 = userRepository.save(new User(null, "Alice Johnson", "alice@example.com", password, "4151254", Set.of(Role.ROLE_USER)));
                User user2 = userRepository.save(new User(null, "Bob Smith", "bob@example.com", password, "4151254", Set.of(Role.ROLE_ADMIN)));

                List<String> dog1Urls = List.of("https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/76296695/1/?bust=1758539460&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/76296695/2/?bust=1758539460&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/76296695/3/?bust=1758539451&width=1080");
                List<String> dog2Urls = List.of("https://dbw3zep4prcju.cloudfront.net/animal/0775a4fd-d85e-42ab-9218-503f8535314c/image/889fb770-3503-4bb7-ae7e-c1e981e4a643.jpeg?versionId=uiquZet402o0B91X0kJjmDkGUlBkWqOB&bust=1754532752&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/0775a4fd-d85e-42ab-9218-503f8535314c/image/51cc3301-5ddd-40d9-923d-49e03326b19c.jpeg?versionId=JIo7LGnTdvKT4tgGX4B9SXQpYx_CAvJI&bust=1754532752&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/0775a4fd-d85e-42ab-9218-503f8535314c/image/a035c342-7ac0-4201-99cb-40514d666b81.jpeg?versionId=yrSkSBB9hAOUTscFJcccanCjbUGPd8vw&bust=1754532752&width=1080");
                List<String> cat2Urls = List.of("https://dbw3zep4prcju.cloudfront.net/animal/e6353247-dff2-4ab2-b5b7-63a15d565352/image/ef16e8d1-bb81-4e32-8ec6-0e297d4737c0.jpg?versionId=L4S6OaPjXN6yRHpymAzJLBcPueOrvcFJ&bust=1756217967&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/e6353247-dff2-4ab2-b5b7-63a15d565352/image/f08b9ac1-1d32-4fc0-9e03-c0ff81f8671b.jpg?versionId=7VsM5NZSJOR2qSYogGl1oC3Oa7i8DJCB&bust=1756217968&width=1080");
                List<String> cat1Urls = List.of("https://dbw3zep4prcju.cloudfront.net/animal/e6769b00-eb41-4b7f-b7a1-16ce4c262fcb/image/091f4dee-a102-4937-91fd-d6e88fe28f67.jpg?versionId=n7.qiyBrq6xNe499l6rydm5OLGzUmnU4&bust=1711902977&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/e6769b00-eb41-4b7f-b7a1-16ce4c262fcb/image/ffaa8580-5c76-418b-9298-538a6cdd363f.jpg?versionId=KU0XzxT5FISPbxYSj5AFnpMJzvUYolf9&bust=1711901597&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/e6769b00-eb41-4b7f-b7a1-16ce4c262fcb/image/7bba65b4-4c05-41be-bdd1-0d10011b6650.jpg?versionId=Zxle7G57SOq4nxLm8tES5k8wUK4.BWjU&bust=1711903104&width=1080");
                List<String> cat3Urls = List.of("https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77682103/1/?bust=1758529534&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77682103/2/?bust=1758529535&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77682103/4/?bust=1758529534&width=1080");
                Pet dog1 = petRepository.save(new Pet(null, "Max", 3, AnimalType.DOG, "Golden Retriever", "Vaccinations up to date, spayed / neutered.", "Affectionate, Friendly, Gentle, Playful, Smart, Loves", dog1Urls, PetStatus.AVAILABLE, clinic1));
                Pet dog2 = petRepository.save(new Pet(null, "Finn", 4, AnimalType.DOG, "Husky", "Vaccinations up to date, spayed / neutered.", "Affectionate, Dignified, Curious, Couch, Friendly, Gentle, Independent, Loves, Loyal, Playful, Smart, Quiet", dog2Urls, PetStatus.AVAILABLE, clinic1));
                Pet cat1 = petRepository.save(new Pet(null, "Whiskers", 2, AnimalType.CAT, "Siamese", "Vaccinations up to date, spayed / neutered.", "Affectionate, Loyal, Gentle, Independent, Quiet, Couch Potato", cat1Urls, PetStatus.ADOPTED, clinic1));
                Pet cat2 = petRepository.save(new Pet(null, "Misha", 1, AnimalType.CAT, "Tabby", "Vaccinations up to date, spayed / neutered.", "Friendly, Affectionate, Playful, Curious, Funny, Snuggly", cat2Urls, PetStatus.AVAILABLE, clinic1));
                Pet cat3 = petRepository.save(new Pet(null, "Tommy", 1, AnimalType.CAT, "Tabby", "Vaccinations up to date, spayed / neutered.", "Friendly, Loyal, Playful, Funny, Couch Potato, Snuggly, Likes To Be Held", cat3Urls, PetStatus.AVAILABLE, clinic1));


                List<String> bird1Urls = List.of("https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77773932/1/?bust=1758818114&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77773932/3/?bust=1758818112&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/77773932/2/?bust=1758818109&width=1080");
                List<String> bird2Urls = List.of("https://dbw3zep4prcju.cloudfront.net/animal/5e819d8d-676b-4d4a-b743-e62618f32e5f/image/b10cf77f-253a-46f3-ad85-f03634655641.jpg?versionId=pdMztSAPx0IsKENVyqTNFguPO3got7n1&bust=1757515927&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/5e819d8d-676b-4d4a-b743-e62618f32e5f/image/5f99bf31-ea22-4f7a-811a-7aaaf41de101.jpg?versionId=g8mEYJgCjgi_G8gQZcjQmMufvUUyBQKv&bust=1757515985&width=1080",
                        "https://dbw3zep4prcju.cloudfront.net/animal/5e819d8d-676b-4d4a-b743-e62618f32e5f/image/7b31b8dc-1db0-4dbf-adbd-a5de86899ca4.jpg?versionId=a8VtVegGu4GuHyk9VmiERpol_1T8SL0s&bust=1757515986&width=1080");
                List<String> bird3Urls = List.of("https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/78380993/1/?bust=1758558904&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/78380993/2/?bust=1758558906&width=1080",
                        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/78380993/3/?bust=1758558906&width=1080");
                Pet bird1 = petRepository.save(new Pet(null, "Mango", 2, AnimalType.BIRD, "Cockatiel", "Vaccinations up to date.", "Chirpy and affectionate", bird1Urls, PetStatus.AVAILABLE, clinic2));
                Pet bird2 = petRepository.save(new Pet(null, "Walker", 1, AnimalType.BIRD, "Pigeon", "Vaccinations up to date.", "Chirpy", bird2Urls, PetStatus.AVAILABLE, clinic2));
                Pet bird3 = petRepository.save(new Pet(null, "Nugget", 1, AnimalType.BIRD, "Parrot", "Vaccinations up to date.", "Affectionate and friendly", bird3Urls, PetStatus.AVAILABLE, clinic2));

                Appointment appointment1 = new Appointment(null, dog1, user1, LocalDateTime.now().plusDays(5), AppointmentStatus.PENDING, AppointmentReason.MEET_AND_GREET);
                Appointment appointment3 = new Appointment(null, dog2, user1, LocalDateTime.now().plusDays(5), AppointmentStatus.CONFIRMED, AppointmentReason.MEET_AND_GREET);

                appointmentRepository.save(appointment1);
                appointmentRepository.save(appointment3);
            }
        };
    }

}
