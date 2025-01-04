package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.UpdatePerformanceMetricsDto;

public interface PerformanceMetricsService {
    ApiResponse createMetricsForLead(Long leadId);
    ApiResponse getTopPerformingLeads();
    ApiResponse getMetricsByLeadId(Long leadId);
    ApiResponse updateMetrics(Long leadId, UpdatePerformanceMetricsDto updateMetricsDto);

}
