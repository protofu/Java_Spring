package com.kosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 생성 수정 시간 자동 작성
@SpringBootApplication
public class BlogProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogProjApplication.class, args);
	}

}
