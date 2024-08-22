package com.kosta.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.Repository.UserRepo;
import com.kosta.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IUserService implements UserService{
	private final UserRepo userRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Long save(User user) {
		// user의 비밀번호를 가져와서 encode 후 bCryptPasswordEncoder 처리하고 다시 set
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepo.save(user).getId();
	}
	
}
