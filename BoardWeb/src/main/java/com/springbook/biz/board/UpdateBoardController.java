package com.springbook.biz.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.view.controller.Controller;

public class UpdateBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

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
		return "getBoardList.do";
	}

}
