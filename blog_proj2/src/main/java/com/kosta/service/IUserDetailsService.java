package com.kosta.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kosta.Repository.UserRepo;
import com.kosta.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IUserDetailsService implements UserDetailsService {
	private final UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email));
		return user;
	}

}
