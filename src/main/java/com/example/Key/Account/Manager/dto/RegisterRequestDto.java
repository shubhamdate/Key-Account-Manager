package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    @NonNull
    private String username;

    @NonNull
//    @Email(message = "Invalid email format")
    private String email;

    @NonNull
    private String password;

    private String role;
}

