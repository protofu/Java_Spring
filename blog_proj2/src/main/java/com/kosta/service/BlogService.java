package com.kosta.service;

import java.util.List;

import com.kosta.entity.Article;

public interface BlogService {

	List<Article> findAll();

	List<Article> searchAndOrder(String keyword, String order);
	
	void save(Article user);

	Article findById(Long id) throws Exception;

	void deleteById(Long id) throws Exception;

	Article update(Article user) throws Exception;
}
