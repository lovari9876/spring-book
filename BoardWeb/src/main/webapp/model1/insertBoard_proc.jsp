<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 글 입력하고 목록으로 가게 해서 view html처리 없어도 된다.
	// 걍 controller 기능을 jsp에서 수행하는 것...

	// 1. 사용자 입력 정보 추출
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setContent(content);

	BoardDAO boardDAO = new BoardDAO();
	boardDAO.insertBoard(vo);

	// 3. 화면 네비게이션
	response.sendRedirect("getBoardList.jsp");
%>
