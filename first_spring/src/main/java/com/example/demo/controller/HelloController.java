package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController // 해당 클래스가 REST Controller 기능을 수행하도록 한다.
public class HelloController {
	@RequestMapping("/") // 매소드가 실행할 수 있는 주소(경로) 설정
	public String hello() {
		return "안녕";
	}
	
	@RequestMapping("/hello") // 매소드가 실행할 수 있는 주소(경로) 설정
	public String hello2() {
		return "ㅋㅋㅋ!!!얍얍얍";
	}
		
}
