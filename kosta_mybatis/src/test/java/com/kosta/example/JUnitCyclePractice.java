package com.kosta.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCyclePractice {

	@BeforeEach
	public void beforeEach() {
		System.out.println("Hello");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("Bye");
	}
	
	@Test
	public void test1() {
		System.out.println("첫번째 테스트");
	}
	
	@Test
	public void test2() {
		System.out.println("두번째 테스트");
	}
	// 각각테스트 시작전 hello 출력
	// 모든 테스트 종료전 Bye 출력
	
}
