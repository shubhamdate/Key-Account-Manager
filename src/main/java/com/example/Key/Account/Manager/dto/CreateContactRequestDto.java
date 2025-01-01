package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequestDto {
    private String name;
    private String role;
    private String phone;
    private String email;
    private Long leadId;
}

