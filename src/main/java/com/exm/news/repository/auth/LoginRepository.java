package com.exm.news.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exm.news.entity.auth.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

}
