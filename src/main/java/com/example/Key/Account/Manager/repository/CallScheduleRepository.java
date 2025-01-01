package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.CallSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CallScheduleRepository extends JpaRepository<CallSchedule, Long> {

    List<CallSchedule> findByNextCallDate(LocalDate nextCallDate);

    Optional<CallSchedule> findByLead_Id(@NonNull Long id);
}
