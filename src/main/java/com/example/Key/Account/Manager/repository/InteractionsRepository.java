package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Interactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionsRepository extends JpaRepository<Interactions, Long> {
}
