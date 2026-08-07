package com.softpro.Project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softpro.Project2.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}