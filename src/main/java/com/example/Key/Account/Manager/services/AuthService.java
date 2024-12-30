package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.LoginRequestDto;
import com.example.Key.Account.Manager.dto.RegisterRequestDto;

public interface AuthService {
    ApiResponse register(RegisterRequestDto requestDto);
    ApiResponse login(LoginRequestDto requestDto);
}
