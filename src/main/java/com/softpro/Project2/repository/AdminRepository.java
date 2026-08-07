package com.softpro.Project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softpro.Project2.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}