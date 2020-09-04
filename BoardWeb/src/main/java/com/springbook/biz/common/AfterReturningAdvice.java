package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {

	@AfterReturning(pointcut = "PointcutCommon.getPointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		// 지정한 모든 패키지 이하의 비즈니스 메소드는 조인포인트가 되어 비즈니스 메서드 호출 시 자동으로 jp 객체 생성됨.
		// advice가 실행될 때 jp 객체가 getName()에 따라 해당 조인포인트 메서드 이름을 가져오고,
		// afterReturning advice라서, advice parameter로 리턴객체가 하나 더 들어감
		String method = jp.getSignature().getName();

		// 비즈니스 메서드의 리턴객체 받아서 UserVO 타입이면, 사후 처리 로직실행.
		// 관리자일 때만 관리자 로그인 안내 뜬다
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			if (user.getRole().contentEquals("admin")) { // 문자열끼리 한글자씩 비교할때(동일 객체 비교아님) 쓴다.
				System.out.println(user.getName() + " 로그인(Admin)");
			}
		}

		System.out.println("[사후 처리] " + method + "() 메소드 리턴값: " + returnObj.toString());

		// 이렇게만 해주고, 두번째 argument로 들어온 바인드 변수 returnObj에 대한 binding을 안해주면
		// unbound 되었다면서 에러 뜬다.
		// returning property를 설정에 추가하여 리턴객체 binding을 반드시!! 해줄것

	}
}
