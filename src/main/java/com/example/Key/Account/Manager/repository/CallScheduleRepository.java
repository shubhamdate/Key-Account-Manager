package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.CallSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallScheduleRepository extends JpaRepository<CallSchedule, Long> {
}
