package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.model.Comment;
@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long>{

}
