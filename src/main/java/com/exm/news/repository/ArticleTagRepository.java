package com.exm.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exm.news.model.ArticleTag;
@Repository
public interface ArticleTagRepository  extends JpaRepository<ArticleTag, Long> {

}
