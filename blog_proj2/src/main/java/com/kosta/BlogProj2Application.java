package com.kosta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 생성 수정 시간 자동 작성
@SpringBootApplication
public class BlogProj2Application {

	public static void main(String[] args) {
		SpringApplication.run(BlogProj2Application.class, args);
	}

}
