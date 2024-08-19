package com.kosta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.User;
import com.kosta.mapper.UserMapper;

@Service
public class IUserService implements UserService{
	@Autowired
	private UserMapper um;

	@Override // 회원 추가
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override // 회원 삭제
	public boolean deleteUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override // 특정 회원 조회
	public User getUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // 모든 회원 조회
	public List<User> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // 회원 정보 수정
	public void modifyUser(User user) throws Exception {
		// TODO Auto-generated method stub
	}

}
