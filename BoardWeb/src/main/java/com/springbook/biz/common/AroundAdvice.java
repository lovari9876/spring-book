package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		// ProceedingJoinPoint exposes(드러내다) the proceed(..) method
		// in order to support around advice in @AJ aspects

		// System.out.println("[BEFORE] 비즈니스 로직 수행 전에 처리할 내용...");
		String method = pjp.getSignature().getName();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object obj = pjp.proceed();
		// Proceed with the next advice or target method invocation

		// System.out.println("[AFTER] 비즈니스 로직 수행 후에 처리할 내용...");
		stopWatch.stop();
		System.out.println(method + "() 메서드 수행에 걸린 시간: " + stopWatch.getTotalTimeMillis() + "(ms)초");

		return obj;
	}
}
