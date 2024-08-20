package com.kosta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kosta.dto.Community;
import com.kosta.service.CommunityService;



@SpringBootTest // 애플리케이션 통합 테이스
@AutoConfigureMockMvc // MockMvc 자동 구성 (일반 컨트롤러 테스트)

class KostaMybatisApplicationTests {
	@Autowired
	protected MockMvc mockMvc; // HTTP 모방
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@DisplayName("커뮤니티 리스트 테스트")
	@Test
	public void communityListTest() throws Exception {
		final String url = "/community/list";
		final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML));
		
		result
			// 응답이 200 ok 인지
			.andExpect(status().isOk())
			// 반환 된 뷰가 community/communitylist 인지
			.andExpect(view().name("community/communitylist"))
			// 모델에 list라는 속성이 있는지
			.andExpect(model().attributeExists("list"))
			// list 모델이 list타입인지
			.andExpect(model().attribute("list", instanceOf(List.class)))
			// list 내부 구성요소가 Community DTO로 구성되어 있는지
			.andExpect(model().attribute("list", everyItem(instanceOf(Community.class))))
			// title 값이 null 이 아닌지
			.andExpect(model().attribute("list", everyItem(HasPropertyWithValue.hasProperty("title", notNullValue()))))
			.andExpect(content().contentType("text/html;charset=UTF-8"));
		}
	

}
