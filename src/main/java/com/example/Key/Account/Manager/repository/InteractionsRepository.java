package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Interactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteractionsRepository extends JpaRepository<Interactions, Long> {

    List<Interactions> findByLead_Id(@NonNull Long id);
}
