package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByLead_Id(@NonNull Long id);

}
