package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateLeadRequestDto;
import com.example.Key.Account.Manager.dto.UpdateLeadRequestDto;
import com.example.Key.Account.Manager.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/lead")
@RestController
public class LeadController {

    private final LeadService leadService;

    @Autowired
    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createLead(@RequestBody CreateLeadRequestDto requestDto) {
        ApiResponse apiResponse = leadService.createLead(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllLeads(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long assignedTo) {
        ApiResponse apiResponse = leadService.getAllLeads(status, assignedTo);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLeadById(@PathVariable Long id) {
        ApiResponse apiResponse = leadService.getLeadById(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLead(@PathVariable Long id, @RequestBody UpdateLeadRequestDto request) {
        ApiResponse apiResponse = leadService.updateLead(id, request);
        return ResponseEntity.ok(apiResponse);
    }
}

