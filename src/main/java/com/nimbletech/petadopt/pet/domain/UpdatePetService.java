package com.nimbletech.petadopt.pet.domain;

import com.nimbletech.petadopt.common.Command;
import com.nimbletech.petadopt.pet.Pet;
import com.nimbletech.petadopt.pet.domain.models.PetDto;
import com.nimbletech.petadopt.pet.domain.models.PetUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePetService implements Command<PetUpdateRequest, PetDto> {

    private final PetRepository petRepository;

    @Override
    @Transactional
    public ResponseEntity<PetDto> execute(PetUpdateRequest updateRequest) {
        log.info("Updating pet with id={}", updateRequest.id());
        return petRepository.findByIdWithAssociations (updateRequest.id())
            .map(existingPet -> {
                existingPet.setName(updateRequest.updatePetDto().name());
                existingPet.setAge(updateRequest.updatePetDto().age());
                existingPet.setType(updateRequest.updatePetDto().type());
                existingPet.setBreed(updateRequest.updatePetDto().breed());
                existingPet.setCharacteristics(updateRequest.updatePetDto().characteristics());
                existingPet.setNeutered(updateRequest.updatePetDto().isNeutered());
                existingPet.setHasMicrochip(updateRequest.updatePetDto().hasMicrochip());
                existingPet.setVaccinated(updateRequest.updatePetDto().isVaccinated());
                existingPet.setImageUrls(updateRequest.updatePetDto().imageUrls());
                Pet updated = petRepository.save(existingPet);
                return ResponseEntity.ok(PetMapper.toDto(updated));
            })
            .orElse(ResponseEntity.notFound().build());
    }
}