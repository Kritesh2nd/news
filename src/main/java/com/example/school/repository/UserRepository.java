package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.school.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query(nativeQuery = true, value="SELECT * FROM user u where u.email = :email")
	User findUserByEmail(String email);
	
	@Query(nativeQuery = true, value="SELECT * FROM user u where u.user_id = :id")
	User findUserById(Long id);
} 