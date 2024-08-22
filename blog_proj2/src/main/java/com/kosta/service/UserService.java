package com.kosta.service;

import java.util.List;

import com.kosta.entity.User;

public interface UserService {

	List<User> findAll();

	List<User> searchAndOrder(String keyword, String order);
	
	void save(User user);

	User findById(Long id) throws Exception;

	void deleteById(Long id) throws Exception;

	User update(User user) throws Exception;
}
