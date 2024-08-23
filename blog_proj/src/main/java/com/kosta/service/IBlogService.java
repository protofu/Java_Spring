package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Article;
import com.kosta.entity.User;
import com.kosta.repository.BlogRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IBlogService implements BlogService{
	
	private final BlogRepo blogRepo;

	@Override
	public Article save(Article article, User user) {
		article.setCreator(user);
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
	public void deleteById(Long id, User user) throws Exception {
		Article article = findById(id);
		User creator = article.getCreator();
		if (user.getId().equals(creator.getId())) {
			blogRepo.deleteById(article.getId()); // id를 그냥 써도 되지만 혹시몰라
		} else {
			throw new Exception("본인이 작성한 글만 삭제할 수 있습니다.");
		}
		
	}

	@Override
	public Article update(Article article, User user) throws Exception {
		Article originArticle = findById(article.getId());
		User creator = originArticle.getCreator();
		if (!user.getId().equals(creator.getId())) {
			throw new Exception("본인이 작성한 글만 수정할 수 있습니다.");
		}

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
