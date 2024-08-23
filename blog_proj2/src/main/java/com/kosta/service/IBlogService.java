package com.kosta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.Repository.BlogRepo;
import com.kosta.entity.Article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IBlogService implements BlogService{
	private final BlogRepo articleRepo;
	
	@Override
	public List<Article> findAll() {
		return articleRepo.findAll();
	}

	@Override
	public List<Article> searchAndOrder(String keyword, String order) {
		if (order.equals("desc")) return articleRepo.findAllByNameContainsOrContentContainsOrderByNameDesc(keyword, keyword);
		return articleRepo.findAllByNameContainsOrContentContainsOrderByName(keyword, keyword);
	}

	@Override
	public void save(Article article) {
		articleRepo.save(article);
	}

	@Override
	public Article findById(Long id) throws Exception {
		Article article = articleRepo.findById(id).orElseThrow(() -> new Exception("없는 유저 입니다."));
		return article;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		articleRepo.deleteById(findById(id).getId());
	}

	@Override
	public Article update(Article article) throws Exception {
		Article originarticle = findById(article.getId());
		originarticle.setName(article.getName());
		originarticle.setContent(article.getContent());
		Article updatedarticle = articleRepo.save(originarticle);
		return updatedarticle;
	}

}
