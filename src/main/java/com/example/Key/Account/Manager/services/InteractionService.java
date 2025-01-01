package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateInteractionRequestDto;
import com.example.Key.Account.Manager.dto.UpdateInteractionRequestDto;

public interface InteractionService {
    ApiResponse createInteraction(CreateInteractionRequestDto requestDto);
    ApiResponse getAllInteractionsByLeadId(Long leadId);
    ApiResponse updateInteraction(Long id, UpdateInteractionRequestDto requestDto);
    ApiResponse deleteInteraction(Long id);
}
