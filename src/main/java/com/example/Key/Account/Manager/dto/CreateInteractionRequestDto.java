package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateInteractionRequestDto {
    private Long leadId;
    private LocalDate interactionDate;
    private String interactionType;
    private String details;
}
