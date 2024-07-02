package com.exm.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exm.news.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}