package com.exm.news.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exm.news.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query(nativeQuery = true, value="SELECT * FROM user u where u.email = :email")
	User findUserByEmail(String email);
	
	@Query(nativeQuery = true, value="SELECT * FROM user u where u.user_id = :id")
	User findUserById(Long id);
	
//	@Query(nativeQuery = true, value="select * from users where username = :user")
//	Optional<User> findUserByUsername(String user);
} 