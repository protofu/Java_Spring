package com.kosta.service;

import java.util.List;

import com.kosta.entity.User;

public interface UserService {
	
	// 전체 리스트 불러오기
	List<User> getAll() throws Exception;
	// 특정 유저 불러오기
	User getUserById(int id) throws Exception;
	// 유저 추가
	void add(User user);
	// 유저 삭제
	void deleteUser(int id);
	// 유저 수정
	void modifyUser(User user) throws Exception;
	// 유저 검색
	List<User> searchUser(String keyword);


}
