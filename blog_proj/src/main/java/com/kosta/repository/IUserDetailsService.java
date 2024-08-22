package com.kosta.repository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kosta.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IUserDetailsService implements UserDetailsService {
	private final UserRepo userRepo;
	
	@Override
	public User loadUserByUsername(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email));
		return user;
	}

}
