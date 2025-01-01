package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateInteractionRequestDto;
import com.example.Key.Account.Manager.dto.UpdateInteractionRequestDto;
import com.example.Key.Account.Manager.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createInteraction(@RequestBody CreateInteractionRequestDto request) {
        ApiResponse response = interactionService.createInteraction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<ApiResponse> getAllInteractionsByLeadId(@PathVariable Long leadId) {
        ApiResponse response = interactionService.getAllInteractionsByLeadId(leadId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateInteraction(@PathVariable Long id, @RequestBody UpdateInteractionRequestDto request) {
        ApiResponse response = interactionService.updateInteraction(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInteraction(@PathVariable Long id) {
        ApiResponse response = interactionService.deleteInteraction(id);
        return ResponseEntity.ok(response);
    }
}
