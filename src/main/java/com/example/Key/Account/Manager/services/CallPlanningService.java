package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateCallPlanRequestDto;

public interface CallPlanningService {
    ApiResponse createCallPlan(CreateCallPlanRequestDto requestDto);
    ApiResponse getTodayCallPlans();
    ApiResponse updateCallFrequency(Long leadId, int frequencyInDays);
    ApiResponse updateLastCallDetails(Long leadId, String lastCallDate);
}