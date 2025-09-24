package com.nimbletech.petadopt.adoption.controller;

import com.nimbletech.petadopt.adoption.dto.AdoptionRequestCreateDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestResponseDTO;
import com.nimbletech.petadopt.adoption.dto.AdoptionRequestUpdateRequest;
import com.nimbletech.petadopt.adoption.dto.UpdateAdoptionRequestDto;
import com.nimbletech.petadopt.adoption.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AdoptionRequestResponseDTO> createRequest(@RequestBody AdoptionRequestCreateDTO dto) {
        return createAdoptionRequestService.execute(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionRequestResponseDTO> updateRequest(@PathVariable Long id, @RequestBody UpdateAdoptionRequestDto dto) {
        AdoptionRequestUpdateRequest updateRequest = new AdoptionRequestUpdateRequest(id, dto);
        return updateAdoptionRequestService.execute(updateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        return deleteAdoptionRequestService.execute(id);
    }
}
