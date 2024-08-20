package com.kosta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
	
	@DisplayName("1+2=3 검증")
	@Test
	public void jUnitTest() {
		// given
		int a = 1;
		int b = 2;
		int sum = 3;
		// when
		int result = a + b;
		// then
		// assertEquals(기대값, 검증값)
		Assertions.assertEquals(sum, result); 
	}
}
