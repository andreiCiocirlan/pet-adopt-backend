package com.nimbletech.petadopt.controller;

import com.nimbletech.petadopt.model.AdoptionRequest;
import com.nimbletech.petadopt.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adoption-requests")
public class AdoptionRequestController {

    private final AdoptionRequestRepository adoptionRequestRepository;

    @GetMapping
    public List<AdoptionRequest> getAllRequests() {
        return adoptionRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequest> getRequestById(@PathVariable Long id) {
        return adoptionRequestRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdoptionRequest createRequest(@RequestBody AdoptionRequest request) {
        return adoptionRequestRepository.save(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionRequest> updateRequest(@PathVariable Long id, @RequestBody AdoptionRequest requestDetails) {
        return adoptionRequestRepository.findById(id)
            .map(request -> {
                request.setStatus(requestDetails.getStatus());
                // other setters if needed
                adoptionRequestRepository.save(request);
                return ResponseEntity.ok(request);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        if (!adoptionRequestRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        adoptionRequestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
