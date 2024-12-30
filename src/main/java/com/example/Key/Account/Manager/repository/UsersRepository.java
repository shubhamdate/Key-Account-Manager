package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsernameIgnoreCase(@NonNull String username);

    Optional<Users> findByEmailIgnoreCase(@NonNull String email);

    Optional<Users> findByUsernameIgnoreCaseOrEmailIgnoreCase(@NonNull String username, @NonNull String email);


}
