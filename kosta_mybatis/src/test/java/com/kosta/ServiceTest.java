package com.kosta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.kosta.dto.Community;
import com.kosta.service.CommunityService;


@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes=KostaMybatisApplication.class)
public class ServiceTest {
	@Autowired
	private CommunityService cs;
	
	@DisplayName("커뮤니티 리스트 서비스 테스트")
	@Test
	public void communityListServiceTest() throws Exception {
		List<Community> commList = cs.getAll();
		
		assertThat(commList).isNotNull();
		assertThat(commList.size()).isEqualTo(1);
		assertThat(commList.get(0).getTitle()).isEqualTo("asd");
	}
}
