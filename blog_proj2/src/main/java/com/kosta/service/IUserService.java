package com.kosta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.Repository.UserRepo;
import com.kosta.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IUserService implements UserService{
	private final UserRepo userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> searchAndOrder(String keyword, String order) {
		if (order.equals("desc")) return userRepo.findAllByNameContainsOrContentContainsOrderByNameDesc(keyword, keyword);
		return userRepo.findAllByNameContainsOrContentContainsOrderByName(keyword, keyword);
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	@Override
	public User findById(Long id) throws Exception {
		User user = userRepo.findById(id).orElseThrow(() -> new Exception("없는 유저 입니다."));
		return user;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		userRepo.deleteById(findById(id).getId());
	}

	@Override
	public User update(User user) throws Exception {
		User originUser = findById(user.getId());
		originUser.setName(user.getName());
		originUser.setContent(user.getContent());
		User updatedUser = userRepo.save(originUser);
		return updatedUser;
	}

}
