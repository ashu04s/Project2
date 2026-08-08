package com.softpro.Project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softpro.Project2.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Home page products
    @Query("""
            SELECT p
            FROM Product p
            WHERE p.product_status = :status
            ORDER BY p.product_id DESC
            """)
    List<Product> findHomeProducts(
            @Param("status") String status
    );

}