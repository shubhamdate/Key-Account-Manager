package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateCallPlanRequestDto;
import com.example.Key.Account.Manager.services.CallPlanningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/call-planning")
public class CallPlanningController {

    private final CallPlanningService callPlanningService;

    @Autowired
    public CallPlanningController(CallPlanningService callPlanningService) {
        this.callPlanningService = callPlanningService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCallPlan(@Valid @RequestBody CreateCallPlanRequestDto requestDto) {
        ApiResponse response = callPlanningService.createCallPlan(requestDto);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse> getTodayCallPlans() {
        ApiResponse response = callPlanningService.getTodayCallPlans();

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{leadId}/frequency")
    public ResponseEntity<ApiResponse> updateCallFrequency(
            @PathVariable Long leadId,
            @RequestParam int frequencyInDays) {
        ApiResponse response = callPlanningService.updateCallFrequency(leadId, frequencyInDays);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{leadId}/last-call")
    public ResponseEntity<ApiResponse> updateLastCallDetails(
            @PathVariable Long leadId,
            @RequestParam LocalDate lastCallDate) {
        ApiResponse response = callPlanningService.updateLastCallDetails(leadId, String.valueOf(lastCallDate));

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
