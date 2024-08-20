package com.kosta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findByName(String name);
}
