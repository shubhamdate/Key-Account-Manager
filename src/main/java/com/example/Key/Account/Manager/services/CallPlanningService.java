package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;

public interface CallPlanningService {
    ApiResponse getTodayCallPlans();
    ApiResponse updateCallFrequency(Long leadId, int frequencyInDays);
    ApiResponse updateLastCallDetails(Long leadId, String lastCallDate);
}