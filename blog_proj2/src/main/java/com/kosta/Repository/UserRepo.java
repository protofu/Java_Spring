package com.kosta.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
