package com.springbook.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// * 모든 Controller들을 같은 타입으로 관리하기 위한 인터페이스
// 클라이언트(웹 브라우저)의 요청을 받은 DispatcherServlet이 HandlerMapping을 통해
// Controller 객체를 검색하고, 검색된 Controller를 실행한다.
// ** 이 때, 어떤 Controller 객체가 검색되더라도 같은 코드로 실행하기 위해 최상위 interface 필요!!
public interface Controller {
	String handleRequest(HttpServletRequest request, HttpServletResponse response);
}

// spring-webmvc-5.2.7.RELEASE.jar 눌러 나오는  org.springframework.web.servlet 이하의
// class와 interface을 잘 살펴보면 mvc 모델 이해하는데 많이 도움된다.
// 지금 하는 실습들도 spring framework에 구현되어 있는 이 binding과 로직들을 이해를 위해 간단히 구현하는 중이기 때문~~~