package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class InsertBoardController {
	// implements Controller 삭제; 이제 Annotation 쓴다.
	// handleRequest()를 Overriding할 필요도, response 객체를 파라미터로 받을 필요도 없어졌다.
	// return type ModelAndView를 계속 쓸 필요도 없어졌다!
	// Cotroller 객체임을 상속을 통해 구현하는게 아니라 POJO 스타일로 구현하게 된 것;
	// @Controller 어노테이션 통해 스프링컨테이너가 자동으로 객체 생성 및, Controller 객체임을 인식한다.

	@RequestMapping("/insertBoard.do") // auto HandlerMapping
	public void insertBoard(HttpServletRequest request/* , HttpServletResponse response */) {

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
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");

	}

}
