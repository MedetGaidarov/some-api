package com.example.backendjavaapijob.domain.user.repository;

import com.example.backendjavaapijob.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
