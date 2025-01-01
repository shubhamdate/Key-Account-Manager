package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateLeadRequestDto;
import com.example.Key.Account.Manager.dto.UpdateLeadRequestDto;

public interface LeadService {
    ApiResponse createLead(CreateLeadRequestDto requestDto);
    ApiResponse getAllLeads(String status, Long assignedTo);
    ApiResponse getLeadById(Long id);
    ApiResponse updateLead(Long id, UpdateLeadRequestDto request);
//    ApiResponse deleteLead(Long id);
}
