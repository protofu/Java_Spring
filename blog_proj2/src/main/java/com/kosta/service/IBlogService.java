package com.kosta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.Repository.BlogRepo;
import com.kosta.entity.Article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IBlogService implements BlogService{
	private final BlogRepo userRepo;
	
	@Override
	public List<Article> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<Article> searchAndOrder(String keyword, String order) {
		if (order.equals("desc")) return userRepo.findAllByNameContainsOrContentContainsOrderByNameDesc(keyword, keyword);
		return userRepo.findAllByNameContainsOrContentContainsOrderByName(keyword, keyword);
	}

	@Override
	public void save(Article user) {
		userRepo.save(user);
	}

	@Override
	public Article findById(Long id) throws Exception {
		Article user = userRepo.findById(id).orElseThrow(() -> new Exception("없는 유저 입니다."));
		return user;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		userRepo.deleteById(findById(id).getId());
	}

	@Override
	public Article update(Article user) throws Exception {
		Article originUser = findById(user.getId());
		originUser.setName(user.getName());
		originUser.setContent(user.getContent());
		Article updatedUser = userRepo.save(originUser);
		return updatedUser;
	}

}
