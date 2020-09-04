package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class DispatcherServletOriginal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get 방식 요청 처리
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	// post 방식 요청 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 깨짐 방지.. 이제 개별 VIEW 페이지에서 인코딩 처리 안해줘도 된다.
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	// 실제로 일처리하는 메서드
	// 분기 처리 로직: if-else if에 따라 처리된다.
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		// Returns the part of this request's URL from the protocolname up to the query
		// string in the first line of the HTTP request.
		String path = uri.substring(uri.lastIndexOf("/")); // 마지막 /부터의 문자열을 path로 지정
		System.out.println(path);

		// 2. 클라이언트의 요청 path에 따라 적절히 분기처리 한다.
		if (path.contentEquals("/login.do")) {
			System.out.println("로그인 처리");

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
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
			
		} else if (path.contentEquals("/logout.do")) {
			System.out.println("로그아웃 처리");
			
			// 1. 브라우저와 연결된 세션을 강제 종료한다.
			HttpSession session = request.getSession();
			session.invalidate();

			// 2. 세션 종료 후, 메인 화면으로 이동한다.
			response.sendRedirect("login.jsp");
			
		} else if (path.contentEquals("/insertBoard.do")) {
			System.out.println("글 등록 처리");
			
			// 1. 사용자 입력 정보 추출
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
			response.sendRedirect("getBoardList.do"); // .do만 해주면 알아서 다시 do요청받고 로직처리함
			
		} else if (path.contentEquals("/updateBoard.do")) {
			System.out.println("글 수정 처리");
			
			// 1. 사용자 입력 정보 추출
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setContent(content);
		 
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);

			// 3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
		} else if (path.contentEquals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			
			// 1. 사용자 입력 정보 추출
			String seq = request.getParameter("seq"); // url뒤의 query String 받아오기

			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);

			// 3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
			
		} else if (path.contentEquals("/getBoard.do")) {
			System.out.println("글 상세 조회 처리");
			
			// 1. 검색할 게시글 번호 추출
			String seq = request.getParameter("seq");

			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. 검색 결과를 세션에 저장하고 상세 화면으로 이동한다.
			HttpSession session = request.getSession(); 
			session.setAttribute("board", board); // 원래는 이렇게 목록을 세션에 저장하면 안돼
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.contentEquals("/getBoardList.do")) {
			System.out.println("글 목록 검색 처리");
			
			// 1. 사용자 입력 정보 추출 (검색 기능은 나중에 구현)
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
			HttpSession session = request.getSession(); 
			session.setAttribute("boardList", boardList); // 원래는 이렇게 목록을 세션에 저장하면 안돼
			response.sendRedirect("getBoardList.jsp");
			
			// ==> interface javax.servlet.http.HttpSession
			// Provides a way to identify a user across more than one page request or visit
			// to a Web site and to store information about that user.

			// The servlet container uses this interface to create a session between an HTTP
			// client and an HTTP server. The session persists for a specified time period,
			// across more than one connection or page request from the user. A session
			// usually corresponds to one user, who may visit a site many times. The server
			// can maintain asession in many ways such as using cookies or rewriting URLs.

			// This interface allows servlets to
			// • View and manipulate information about a session, such as the session
			// identifier, creation time, and last accessed time
			// • Bind objects to sessions, allowing user information to persist across
			// multiple user connections; 세션과 객체 바인딩으로, 유저 여럿이 접속하는 와중에도 유저정보를 지속할 수 있음.
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
