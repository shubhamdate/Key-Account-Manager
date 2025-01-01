package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLeadRequestDto {
    private String name;
    private String address;
    private String status;
    private Long assignedTo;
}
