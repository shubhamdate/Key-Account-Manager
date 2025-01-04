package com.example.Key.Account.Manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "performance_metrics", schema = "kam")
public class PerformanceMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private Leads lead;

    @Column(name = "total_orders")
    private Integer totalOrders;

    @Column(name = "total_revenue", precision = 18, scale = 2)
    private BigDecimal totalRevenue;

    @Column(name = "average_order_value", precision = 18, scale = 2)
    private BigDecimal averageOrderValue;

    @Column(name = "last_order_date")
    private LocalDate lastOrderDate;
}
