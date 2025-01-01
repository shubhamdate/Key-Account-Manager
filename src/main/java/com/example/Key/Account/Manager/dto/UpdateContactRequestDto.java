package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactRequestDto {
    private String name;
    private String role;
    private String phone;
    private String email;
}