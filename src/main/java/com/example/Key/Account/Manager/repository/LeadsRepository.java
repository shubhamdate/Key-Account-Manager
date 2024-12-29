package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadsRepository extends JpaRepository<Leads, Long> {
}
