package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

// 무조건 실행되는 거라서 afterThrowing이나 afterReturning보다도 먼저 실행된다.
@Service
@Aspect
public class AfterAdvice {

	/*
	 * @Pointcut("execution(* com.springbook.biz..*Impl.*(..))") public void
	 * allPointcut() { }
	 */

	@After("PointcutCommon.allPointcut()")
	public void finallyLog() {
		System.out.println("[사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
