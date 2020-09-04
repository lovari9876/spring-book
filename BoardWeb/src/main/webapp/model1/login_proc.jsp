<%@page import="com.springbook.biz.user.impl.UserDAO"%>
<%@page import="com.springbook.biz.user.UserVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 글 입력하고 목록으로 가게 해서 view html처리 없어도 된다.
	// 걍 controller 기능을 jsp에서 수행하는 것...

	// 1. 사용자 입력 정보 추출 
	String id = request.getParameter("id");
	String password = request.getParameter("password");

	// 2. DB 연동 처리
	UserVO vo = new UserVO();
	vo.setId(id);
	vo.setPassword(password);

	UserDAO userDAO = new UserDAO();
	UserVO user = userDAO.getUser(vo);

	// 3. 화면 네비게이션
	if (user != null) {
		response.sendRedirect("getBoardList.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
%>