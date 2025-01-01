package com.example.Key.Account.Manager.services;

import com.example.Key.Account.Manager.dto.ApiResponse;

public interface UserService {
    ApiResponse findUserByUsername(String username);
    ApiResponse getAllUsers();
    ApiResponse getUserById(Long id);
    ApiResponse deleteUser(Long id);
}
