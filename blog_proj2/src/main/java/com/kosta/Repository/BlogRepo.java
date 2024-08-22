package com.kosta.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.Article;

@Repository
public interface BlogRepo extends JpaRepository<Article, Long>{

	List<Article> findAllByNameContainsOrContentContainsOrderByName(String keyword, String keyword2);

	List<Article> findAllByNameContainsOrContentContainsOrderByNameDesc(String keyword, String keyword2);

}
