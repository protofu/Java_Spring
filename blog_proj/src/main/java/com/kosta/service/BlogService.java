package com.kosta.service;

import java.util.List;

import com.kosta.entity.Article;
import com.kosta.entity.User;

public interface BlogService {
	
	// save
	Article save(Article article, User user);
	
	// findAll
	List<Article> findAll();
	
	// findById
	Article findById(Long id) throws Exception;
	
	// deleteById
	void deleteById(Long id, User user) throws Exception;
	
	// updateById
	Article update(Article article, User user) throws Exception;

	// search & ordering
	List<Article> searchAndOrder(String keyword, String order);
}
