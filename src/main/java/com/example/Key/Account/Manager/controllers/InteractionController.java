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
@RequestMapping("/api/interaction")
public class InteractionController {

    private final InteractionService interactionService;

    @Autowired
    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createInteraction(@RequestBody CreateInteractionRequestDto request) {
        ApiResponse response = interactionService.createInteraction(request);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<ApiResponse> getAllInteractionsByLeadId(@PathVariable Long leadId) {
        ApiResponse response = interactionService.getAllInteractionsByLeadId(leadId);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateInteraction(@PathVariable Long id, @RequestBody UpdateInteractionRequestDto request) {
        ApiResponse response = interactionService.updateInteraction(id, request);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInteraction(@PathVariable Long id) {
        ApiResponse response = interactionService.deleteInteraction(id);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
