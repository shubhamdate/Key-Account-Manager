package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.entities.Users;
import com.example.Key.Account.Manager.repository.UsersRepository;
import com.example.Key.Account.Manager.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public ApiResponse findUserByUsername(String username){
        Optional<Users> optUser = usersRepository.findByUsernameIgnoreCase(username);
        return new ApiResponse<>("success", optUser, null) ;
    }

    @Transactional
    public ApiResponse getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        return new ApiResponse<>("success", usersList, null);
    }

    @Transactional
    public ApiResponse getUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        if(user.isEmpty()) {
            return new ApiResponse<>("error",null, "user not found");
        }

        return new ApiResponse<>("success",user, null);
    }


    @Transactional
    public ApiResponse deleteUser(Long id) {

        Optional<Users> user = usersRepository.findById(id);
        if(user.isEmpty()) {
            return new ApiResponse<>("error",null, "user not found");
        }

        usersRepository.deleteById(id);
        return new ApiResponse<>("success", null, "user deleted successfully");
    }
}

