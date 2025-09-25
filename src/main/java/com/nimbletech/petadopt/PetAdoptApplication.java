package com.nimbletech.petadopt;

import com.nimbletech.petadopt.adoption.model.AdoptionRequest;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.adoption.repository.AdoptionRequestRepository;
import com.nimbletech.petadopt.person.model.Person;
import com.nimbletech.petadopt.person.model.PersonStatus;
import com.nimbletech.petadopt.person.repository.PersonRepository;
import com.nimbletech.petadopt.pet.model.AnimalType;
import com.nimbletech.petadopt.pet.model.Pet;
import com.nimbletech.petadopt.pet.model.PetStatus;
import com.nimbletech.petadopt.pet.repository.PetRepository;
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
                Person person1 = personRepository.save(new Person(null, "Alice Johnson", "alice@example.com", PersonStatus.APPLICANT));
                Person person2 = personRepository.save(new Person(null, "Bob Smith", "bob@example.com", PersonStatus.ADOPTER));

                Pet pet1 = petRepository.save(new Pet(null, "Max", 3, AnimalType.DOG, "Golden Retriever", "healthy", "12345", "https://images.dog.ceo/breeds/retriever-golden/20200801_174527_200801.jpg", PetStatus.AVAILABLE));
                Pet pet2 = petRepository.save(new Pet(null, "Whiskers", 2, AnimalType.CAT, "Siamese", "healthy", "23456", "https://cdn2.thecatapi.com/images/e6n.jpg", PetStatus.ADOPTED));

                AdoptionRequest request1 = new AdoptionRequest(null, pet1, person1, LocalDateTime.now(), AdoptionStatus.PENDING);
                AdoptionRequest request2 = new AdoptionRequest(null, pet2, person2, LocalDateTime.now().minusDays(10), AdoptionStatus.APPROVED);

                adoptionRequestRepository.save(request1);
                adoptionRequestRepository.save(request2);
            }
        };
    }

}
