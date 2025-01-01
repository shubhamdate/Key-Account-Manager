package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.services.CallPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/call-planning")
public class CallPlanningController {

    private final CallPlanningService callPlanningService;

    @Autowired
    public CallPlanningController(CallPlanningService callPlanningService) {
        this.callPlanningService = callPlanningService;
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse> getTodayCallPlans() {
        ApiResponse response = callPlanningService.getTodayCallPlans();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{leadId}/frequency")
    public ResponseEntity<ApiResponse> updateCallFrequency(
            @PathVariable Long leadId,
            @RequestParam int frequencyInDays) {
        ApiResponse response = callPlanningService.updateCallFrequency(leadId, frequencyInDays);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{leadId}/last-call")
    public ResponseEntity<ApiResponse> updateLastCallDetails(
            @PathVariable Long leadId,
            @RequestParam String lastCallDate) {
        ApiResponse response = callPlanningService.updateLastCallDetails(leadId, lastCallDate);
        return ResponseEntity.ok(response);
    }
}
