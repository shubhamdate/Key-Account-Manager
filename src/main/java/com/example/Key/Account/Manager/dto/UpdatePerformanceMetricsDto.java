package com.example.Key.Account.Manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePerformanceMetricsDto {
    private int totalOrders;
    private BigDecimal totalRevenue;
    private String lastOrderDate;
}
