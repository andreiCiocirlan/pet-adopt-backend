package com.nimbletech.petadopt.adoption.controller;

import com.nimbletech.petadopt.adoption.dto.AdoptionRequestCreateDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestUpdateRequest;
import com.nimbletech.petadopt.adoption.dto.AdoptionStatusUpdateDto;
import com.nimbletech.petadopt.adoption.model.AdoptionStatus;
import com.nimbletech.petadopt.adoption.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/adoptions")
public class AdoptionRequestController {

    private final GetAdoptionRequestsService getAdoptionRequestsService;
    private final GetAdoptionRequestByIdService getAdoptionRequestByIdService;
    private final CreateAdoptionRequestService createAdoptionRequestService;
    private final UpdateAdoptionRequestService updateAdoptionRequestService;
    private final DeleteAdoptionRequestService deleteAdoptionRequestService;

    @GetMapping
    public ResponseEntity<List<AdoptionRequestResponseDTO>> getAllRequests() {
        return getAdoptionRequestsService.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequestResponseDTO> getRequestById(@PathVariable Long id) {
        return getAdoptionRequestByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<AdoptionRequestResponseDTO> createRequest(@RequestBody AdoptionRequestCreateDTO dto, Authentication authentication) {
        return createAdoptionRequestService.execute(dto);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<AdoptionRequestResponseDTO> approveRequest(@PathVariable Long id) {
        AdoptionStatusUpdateDto dto = new AdoptionStatusUpdateDto(AdoptionStatus.APPROVED);
        AdoptionRequestUpdateRequest updateRequest = new AdoptionRequestUpdateRequest(id, dto);
        return updateAdoptionRequestService.execute(updateRequest);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<AdoptionRequestResponseDTO> rejectRequest(@PathVariable Long id) {
        AdoptionStatusUpdateDto dto = new AdoptionStatusUpdateDto(AdoptionStatus.REJECTED);
        AdoptionRequestUpdateRequest updateRequest = new AdoptionRequestUpdateRequest(id, dto);
        return updateAdoptionRequestService.execute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        return deleteAdoptionRequestService.execute(id);
    }
}
