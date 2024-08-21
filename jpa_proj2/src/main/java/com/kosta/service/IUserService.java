package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.entity.User;
import com.kosta.repository.UserRepo;

@Service
public class IUserService implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<User> getAll() throws Exception {
		return userRepo.findAll();
	}

	@Override
	public void add(User user) {
		userRepo.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}
	
	@Override
	public User getUserById(int id) throws Exception {
		Optional<User> optUser = userRepo.findById(id);
		User user = optUser.orElseThrow(() -> new Exception("없는 유저 입니다"));
		return user;
	}
	
	@Override
	public void modifyUser(User user) throws Exception {
		User upUser = getUserById(user.getId());
		upUser.setName(user.getName());
		upUser.setEmail(user.getEmail());
		userRepo.save(upUser);
	}

	@Override
	public List<User> searchUser(String keyword) {
		return userRepo.findByNameContains(keyword);
	}

	
	
}
