package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private String status;
    private T data;
    private T message;

    public ApiResponse(String status, T data, T message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
