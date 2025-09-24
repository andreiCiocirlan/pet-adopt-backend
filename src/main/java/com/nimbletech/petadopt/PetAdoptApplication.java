package com.nimbletech.petadopt;

import com.nimbletech.petadopt.model.AdoptionRequest;
import com.nimbletech.petadopt.model.Person;
import com.nimbletech.petadopt.model.Pet;
import com.nimbletech.petadopt.model.UserStatus;
import com.nimbletech.petadopt.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.repository.PersonRepository;
import com.nimbletech.petadopt.repository.PetRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class PetAdoptApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetAdoptApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(PetRepository petRepository,
                                        PersonRepository personRepository,
                                        AdoptionRequestRepository adoptionRequestRepository) {
        return args -> {
            boolean emptyDatabase = petRepository.count() == 0 && personRepository.count() == 0 && adoptionRequestRepository.count() == 0;
            if (emptyDatabase) {
                Person person1 = personRepository.save(new Person(null, "Alice Johnson", "alice@example.com", UserStatus.APPLICANT));
                Person person2 = personRepository.save(new Person(null, "Bob Smith", "bob@example.com", UserStatus.ADOPTER));

                Pet pet1 = petRepository.save(new Pet(null, "Max", 3, "Dog", "Golden Retriever", "healthy", "available"));
                Pet pet2 = petRepository.save(new Pet(null, "Whiskers", 2, "Cat", "Siamese", "healthy", "available"));

                AdoptionRequest request1 = new AdoptionRequest(null, pet1, person1, LocalDateTime.now(), "pending");
                AdoptionRequest request2 = new AdoptionRequest(null, pet2, person2, LocalDateTime.now().minusDays(10), "approved");

                adoptionRequestRepository.save(request1);
                adoptionRequestRepository.save(request2);
            }
        };
    }

}
