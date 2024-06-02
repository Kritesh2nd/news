package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	public String getByEmail(String email);
}