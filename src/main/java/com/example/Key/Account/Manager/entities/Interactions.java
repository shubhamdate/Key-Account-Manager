package com.example.Key.Account.Manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "interactions", schema = "kam")
public class Interactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Leads lead;

    @Column(name = "interactionType")
    private String interactionType;

    @Column(name = "details")
    private String details;

    @Column(name = "interaction_date")
    private LocalDate interactionDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
