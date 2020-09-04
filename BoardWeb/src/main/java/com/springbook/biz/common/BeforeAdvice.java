package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service // Intended for use by tools and aspects (making an ideal target for pointcuts).
@Aspect
public class BeforeAdvice {

	@Before("PointcutCommon.allPointcut()") 
	// 외부 클래스 포인트 컷 참조시, 클래스를 지정해주지 않으면 다음과 같은 에러 발생
	// can't find referenced pointcut allPointcut
	public void beforeLog(JoinPoint jp) {

		String method = jp.getSignature().getName();
		// Returns the identifier part of this signature. For methods this will return
		// the method name.
		Object[] args = jp.getArgs(); // Returns the arguments at this join point.

		System.out.println("[사전 처리] " + method + "() 메소드 ARGS 정보: " + args[0].toString());

	}
}
