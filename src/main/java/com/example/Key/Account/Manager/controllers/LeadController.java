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
        ApiResponse response = leadService.createLead(requestDto);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllLeads(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long assignedTo) {
        ApiResponse response = leadService.getAllLeads(status, assignedTo);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLeadById(@PathVariable Long id) {
        ApiResponse response = leadService.getLeadById(id);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLead(@PathVariable Long id, @RequestBody UpdateLeadRequestDto request) {
        ApiResponse response = leadService.updateLead(id, request);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

