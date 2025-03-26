package com.example.agenda_fit_v2.repository;

import com.example.agenda_fit_v2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByLoginOrEmail(String login, String email);

    @Query("SELECT a FROM Users a WHERE (a.login = :login OR a.email = :email) AND a.id_user <> :userId ")
    Optional<Users> findByLoginOrEmailExcludingId(String login, String email, UUID userId);
}
