package com.kosta.service;

import java.util.List;

import com.kosta.dto.User;

public interface UserService {
	// 회원 추가
	boolean addUser(User user) throws Exception;
	// 회원 삭제
	boolean deleteUser(int id) throws Exception;
	// 특정 회원 조회
	User getUserById(int id) throws Exception;
	// 모든 회원 조회
	List<User> getAll() throws Exception;
	// 회원 정보 수정
	void modifyUser(User user) throws Exception;
}
