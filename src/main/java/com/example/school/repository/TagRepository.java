package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.model.Tag;
@Repository
public interface TagRepository  extends JpaRepository<Tag, Long>{

}
