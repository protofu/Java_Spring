package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Article;
import com.kosta.repository.BlogRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IBlogService implements BlogService{
	
	private final BlogRepo blogRepo;

	@Override
	public Article save(Article article) {
		return blogRepo.save(article);
	}

	@Override
	public List<Article> findAll() {
		return blogRepo.findAll();
	}

	@Override
	public Article findById(Long id) throws Exception {
		Optional<Article> optArticle = blogRepo.findById(id);
		Article article = optArticle.orElseThrow(() -> new Exception("없는 ID 임돠"));
		return article;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Article article = findById(id);
		blogRepo.deleteById(article.getId()); // id를 그냥 써도 되지만 혹시몰라
		
	}

	@Override
	public Article update(Article article) throws Exception {
		Article originArticle = findById(article.getId());
		originArticle.setTitle(article.getTitle());
		originArticle.setContent(article.getContent());
		
		Article savedArticle = blogRepo.save(originArticle);
		return savedArticle;
	}

	@Override
	public List<Article> searchAndOrder(String keyword, String order) {
		if (order.equals("desc")) {
			// 내림차순
			return blogRepo.findAllByTitleContainsOrContentContainsOrderByTitleDesc(keyword, keyword);
		}
		// 오름차순
		return blogRepo.findAllByTitleContainsOrContentContainsOrderByTitle(keyword, keyword);
	}

}
