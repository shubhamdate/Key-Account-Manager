package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.UpdatePerformanceMetricsDto;
import com.example.Key.Account.Manager.services.PerformanceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/performance")
public class PerformanceMetricsController {

    private final PerformanceMetricsService performanceMetricsService;

    @Autowired
    public PerformanceMetricsController(PerformanceMetricsService performanceMetricsService) {
        this.performanceMetricsService = performanceMetricsService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPerformanceMetrics(@RequestParam Long leadId) {
        ApiResponse response = performanceMetricsService.createMetricsForLead(leadId);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/top")
    public ResponseEntity<ApiResponse> getTopPerformingLeads() {
        ApiResponse response = performanceMetricsService.getTopPerformingLeads();

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{leadId}")
    public ResponseEntity<ApiResponse> getMetricsByLeadId(@PathVariable Long leadId) {
        ApiResponse response = performanceMetricsService.getMetricsByLeadId(leadId);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{leadId}")
    public ResponseEntity<ApiResponse> updatePerformanceMetrics(
            @PathVariable Long leadId,
            @RequestBody UpdatePerformanceMetricsDto updateMetricsDto) {
        ApiResponse response = performanceMetricsService.updateMetrics(leadId, updateMetricsDto);

        if(response.getStatus().equals("error")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
