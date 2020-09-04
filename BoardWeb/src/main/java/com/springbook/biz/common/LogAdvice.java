package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

// AOP(Aspect Oriented Programming)을 하기위해서 
// 공통 관심(cross-cutting concerns) 로직 예시 만들어보기

@Service // advice도 service annotation 사용
@Aspect // Aspect = Pointcut + Advice => weaving configuration
// aspect annotation 붙이고, pointcut하고 advice 어노테이션만 붙이면 자동 weaving!!!
public class LogAdvice {

	// annotation으로 처리할 때에는 포인트컷을 식별할 식별자만 있으면 되기에 body가 있긴한데,
	// body 구현 내용이 없는 '참조 메소드'로 만든다. => 식별하는 용도

	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
	}

	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {
	}

	// *** advice method ***
	@Before("allPointcut()")
	public void printLog(JoinPoint jp) {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}

}
