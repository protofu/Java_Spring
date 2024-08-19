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
	public boolean addUser(User user) {
		try {
			um.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override // 회원 삭제
	public boolean deleteUser(int id) {
		try {
			um.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override // 특정 회원 조회
	public User getUserById(int id) throws Exception {
		User user = um.findById(id);
		return user;
	}

	@Override // 모든 회원 조회
	public List<User> getAll() throws Exception {
		List<User> userList = um.findAll();
		return userList;
	}

	@Override // 회원 정보 수정
	public User modifyUser(User user) {
		try {
			um.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
