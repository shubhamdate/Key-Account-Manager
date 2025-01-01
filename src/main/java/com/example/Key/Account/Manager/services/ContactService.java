package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateContactRequestDto;
import com.example.Key.Account.Manager.dto.UpdateContactRequestDto;

public interface ContactService {
    ApiResponse createContact(CreateContactRequestDto requestDto);
    ApiResponse getAllContactsByLeadId(Long leadId);
    ApiResponse updateContact(Long id, UpdateContactRequestDto requestDto);
    ApiResponse deleteContact(Long id);
}
