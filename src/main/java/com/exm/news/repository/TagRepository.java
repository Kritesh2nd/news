package com.exm.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exm.news.model.Tag;
@Repository
public interface TagRepository  extends JpaRepository<Tag, Long>{

}
