package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.Comment;

public interface CommentRepository  extends JpaRepository<Comment, Long>{

}
