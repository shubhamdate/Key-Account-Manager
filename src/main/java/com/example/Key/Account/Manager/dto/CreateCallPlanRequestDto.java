package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateCallPlanRequestDto {
    private Long leadId;
    private int callFrequency;
    private LocalDate nextCallDate;
}
