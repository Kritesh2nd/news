package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school.model.ArticleTag;
@Repository
public interface ArticleTagRepository  extends JpaRepository<ArticleTag, Long> {

}
