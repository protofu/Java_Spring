package com.kosta.service;

import java.util.List;

import com.kosta.entity.Article;

public interface BlogService {
	
	// save
	Article save(Article article);
	
	// findAll
	List<Article> findAll();
	
	// findById
	Article findById(Long id) throws Exception;
	
	// deleteById
	void deleteById(Long id) throws Exception;
	
	// updateById
	Article update(Article article) throws Exception;
	
	// search
	List<Article> searchInTitleAndContent(String keyword);
	
	// ordering
	List<Article> orderingArticle(String order);
}
