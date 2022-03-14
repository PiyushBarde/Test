package com.bridgelabz.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
        value = "SELECT * from user_details WHERE email= :email",
        nativeQuery = true
    )
    Optional<User> findByEmail(String email);
}