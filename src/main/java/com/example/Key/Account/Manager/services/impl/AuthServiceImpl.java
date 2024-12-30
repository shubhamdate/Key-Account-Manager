package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.SecurityConfig.JwtUtils;
import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.LoginRequestDto;
import com.example.Key.Account.Manager.dto.RegisterRequestDto;
import com.example.Key.Account.Manager.entities.Users;
import com.example.Key.Account.Manager.repository.UsersRepository;
import com.example.Key.Account.Manager.services.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    public AuthServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public ApiResponse register(RegisterRequestDto requestDto){

        Optional<Users> userByUsername = usersRepository.findByUsernameIgnoreCase(requestDto.getUsername());
         if(userByUsername.isPresent()) {
             return new ApiResponse("error", null, "Username already exits");
         }

        Optional<Users> usersByEmail = usersRepository.findByEmailIgnoreCase(requestDto.getEmail());
        if(usersByEmail.isPresent()) {
            return new ApiResponse("error", null, "Email already exits");
        }

        Users user = new Users();

        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setRole(requestDto.getRole());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        usersRepository.save(user);
        return new ApiResponse("sucsess", null, "User Register Successfully");
    }

    @Transactional
    public ApiResponse login(LoginRequestDto loginRequest) {
        Optional<Users> userOptional = usersRepository.findByUsernameIgnoreCase(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtils.generateToken(user.getUsername());
                return new ApiResponse("success", token, "Login successful");
            }
        }
        return new ApiResponse("error", null, "Invalid credentials");
    }
}
