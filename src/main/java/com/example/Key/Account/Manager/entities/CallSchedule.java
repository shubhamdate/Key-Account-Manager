package com.example.Key.Account.Manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "kam.call_schedule")
public class CallSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = false)
    private CallSchedule callSchedule;

    @Column(name = "call_frequency")
    private Integer callFrequency;

    @Column(name = "last_call_date")
    private LocalDate lastCallDate;

    @Column(name = "next_call_date")
    private LocalDate nextCallDate;
}
