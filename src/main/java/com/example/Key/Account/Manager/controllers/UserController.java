package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.repository.UsersRepository;

public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
