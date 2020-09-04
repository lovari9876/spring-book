package com.springbook.biz.common;

// AOP(Aspect Oriented Programming)을 하기위해서 
// 공통 관심(cross-cutting concerns) 로직 예시 만들어보기
public class Log4jAdvice {
	public void printLogging() {
		System.out.println("[공통 로그-Log4j] 비즈니스 로직 수행 전 동작");
	}

}
