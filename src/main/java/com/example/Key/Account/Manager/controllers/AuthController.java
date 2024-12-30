package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.LoginRequestDto;
import com.example.Key.Account.Manager.dto.RegisterRequestDto;
import com.example.Key.Account.Manager.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }


//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
//        authService.logout(token);
//        return ResponseEntity.ok("Logged out successfully.");
//    }

//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
//        return ResponseEntity.ok(authService.refreshToken(request));
//    }
}

