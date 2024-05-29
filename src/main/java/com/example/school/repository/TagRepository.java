package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.model.Tag;

public interface TagRepository  extends JpaRepository<Tag, Long>{

}
