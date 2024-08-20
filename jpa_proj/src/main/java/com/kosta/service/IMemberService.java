package com.kosta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.entity.Member;
import com.kosta.repository.MemberRepository;

@Service
public class IMemberService implements MemberService{
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public List<Member> getAll() throws Exception {
		return memberRepo.findAll();
	}

	@Override
	public void insertMember(Member member) throws Exception {
		memberRepo.save(member);
	}

}
