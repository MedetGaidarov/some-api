package com.example.backendjavaapijob.domain.user.repository;

import com.example.backendjavaapijob.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
