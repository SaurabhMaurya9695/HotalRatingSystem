package com.user.service.userservice.repository;

import com.user.service.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , String> {
    Optional<User> findByEmail(String email);
}
