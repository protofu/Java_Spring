package com.kosta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.kosta.entity.Member;
import com.kosta.repository.MemberRepository;

import jakarta.transaction.Transactional;

@DataJpaTest // JPA 테스트
@AutoConfigureTestDatabase(replace=Replace.NONE) // 실제 DB로 Test
public class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;
	
	@DisplayName("전체 회원 조회")
	@Sql("/data.sql") // 테스트 실행 전 SQL문(INSERT 3개) 실행
	@Test
	public void getAllMemeberss() {
		List<Member> allMembers = memberRepository.findAll();
		for(Member m : allMembers) {
			System.out.println(m);
		}
		assertThat(allMembers.size()).isEqualTo(3);
	}
	
	@DisplayName("특정 회원 조회")
	@Sql("/data.sql") // 테스트 실행 전 SQL문(INSERT 3개) 실행
	@Test
	public void getMemberBySql() {
		Optional<Member> optionalMember = memberRepository.findById(2);
		Member member = optionalMember.get();
		System.out.println(member);
		assertThat(member.getName()).isEqualTo("도우너");
	}

	@DisplayName("회원 추가")
	@Sql("/data.sql") // 테스트 실행 전 SQL문(INSERT 3개) 실행
	@Test
	public void insertMember() {
		Member member = new Member();
		member.setId(4);
		member.setName("쌀과자");
		Member savedMember = memberRepository.save(member);
		System.out.println(savedMember);
		assertThat(savedMember).isEqualTo(memberRepository.findByName("쌀과자").get()); 
	}

	@DisplayName("회원 삭제")
	@Sql("/data.sql") // 테스트 실행 전 SQL문(INSERT 3개) 실행
	@Test
	public void deleteMember() {
		memberRepository.deleteById(3);
		assertThat(memberRepository.count()).isEqualTo(2); 
	}

	@DisplayName("회원 삭제")
	@Sql("/data.sql") // 테스트 실행 전 SQL문(INSERT 3개) 실행
	@Test
	@Transactional // 업데이트를 위해 사용 (기본은 DataJpaTest에 숨어있음)
	public void updateMemberName() {
		Member member = memberRepository.findById(3).get();
		member.setName("고기동");
		assertThat(memberRepository.findByName("고기동").get()).isEqualTo(member); 
	}
}
