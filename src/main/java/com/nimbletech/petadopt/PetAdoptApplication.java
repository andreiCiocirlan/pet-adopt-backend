package com.nimbletech.petadopt;

import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
import com.nimbletech.petadopt.user.model.User;
import com.nimbletech.petadopt.user.model.UserStatus;
import com.nimbletech.petadopt.user.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class PetAdoptApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(BCryptPasswordEncoder passwordEncoder,
                                        PetRepository petRepository,
                                        UserRepository userRepository,
                                        AdoptionRequestRepository adoptionRequestRepository) {
        return args -> {
            boolean emptyDatabase = petRepository.count() == 0 && userRepository.count() == 0 && adoptionRequestRepository.count() == 0;
            if (emptyDatabase) {
                String password = passwordEncoder.encode("test123");
                User user1 = userRepository.save(new User(null, "Alice Johnson", "alice@example.com", password, UserStatus.APPLICANT));
                User user2 = userRepository.save(new User(null, "Bob Smith", "bob@example.com", password, UserStatus.ADOPTER));

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
                Pet pet1 = petRepository.save(new Pet(null, "Max", 3, AnimalType.DOG, "Golden Retriever", "healthy", "12345", dog1Urls, PetStatus.AVAILABLE));
                Pet pet4 = petRepository.save(new Pet(null, "Finn", 4, AnimalType.DOG, "Husky", "healthy", "12345", dog2Urls, PetStatus.AVAILABLE));
                Pet pet2 = petRepository.save(new Pet(null, "Whiskers", 2, AnimalType.CAT, "Siamese", "healthy", "23456", cat1Urls, PetStatus.ADOPTED));
                Pet pet3 = petRepository.save(new Pet(null, "Misha", 1, AnimalType.CAT, "Tabby", "healthy", "23456", cat2Urls, PetStatus.AVAILABLE));
                Pet pet5 = petRepository.save(new Pet(null, "Tommy", 1, AnimalType.CAT, "Tabby", "healthy", "23456", cat3Urls, PetStatus.AVAILABLE));

                AdoptionRequest request1 = new AdoptionRequest(null, pet1, user1, LocalDateTime.now(), AdoptionStatus.PENDING);
                AdoptionRequest request2 = new AdoptionRequest(null, pet2, user2, LocalDateTime.now().minusDays(10), AdoptionStatus.APPROVED);

                adoptionRequestRepository.save(request1);
                adoptionRequestRepository.save(request2);
            }
        };
    }

}
