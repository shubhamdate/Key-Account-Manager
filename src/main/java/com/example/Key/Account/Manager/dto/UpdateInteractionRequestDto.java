package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateInteractionRequestDto {
    private LocalDate interactionDate;
    private String interactionType;
    private String details;
}