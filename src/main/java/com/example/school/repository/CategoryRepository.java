package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
