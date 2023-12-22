package com.example.useradvisormanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.useradvisormanagement.domain.UserAdvise;

@Repository
public interface UserRepository extends JpaRepository<UserAdvise, Long> {
    Optional<UserAdvise> findByUsername(String username);

    Optional<UserAdvise> findByEmail(String email);
}