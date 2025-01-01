package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadsRepository extends JpaRepository<Leads, Long> {

    List<Leads> findByStatusIgnoreCase(@NonNull String status);

    List<Leads> findByUser_Id(@NonNull Long id);

    List<Leads> findByStatusIgnoreCaseAndUser_Id(@NonNull String status, @NonNull Long id);

}
