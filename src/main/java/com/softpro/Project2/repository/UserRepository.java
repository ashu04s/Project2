package com.softpro.Project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softpro.Project2.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

}