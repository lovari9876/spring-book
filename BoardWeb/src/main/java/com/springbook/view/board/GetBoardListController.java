package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class GetBoardListController implements org.springframework.web.servlet.mvc.Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("글 목록 검색 처리");

		// 1. 사용자 입력 정보 추출 (검색 기능은 나중에 구현)

		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);

//		controller 직접 만들어 썼을 때
//		// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
//		HttpSession session = request.getSession();
//		session.setAttribute("boardList", boardList); // 원래는 이렇게 목록을 세션에 저장하면 안돼
//		return "getBoardList";

		// 3. 검색 결과와 화면 정보를 ModelAndView에 저장하여 리턴한다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // Model 정보 저장
		mav.setViewName("getBoardList.jsp"); // View 정보 저장

		return mav;
	}

}