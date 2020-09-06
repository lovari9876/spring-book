package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

// Annotation 사용하여 간단해진 컨트롤러들을 게시판, 회원관리 두 가지로 각각 한 데 묶어보자

@Controller
public class BoardController {
	// implements Controller 삭제; 이제 Annotation 쓴다.
	// handleRequest()를 Overriding할 필요도, response 객체를 파라미터로 받을 필요도 없어졌다.
	// return type ModelAndView를 계속 쓸 필요도 없어졌다!
	// Cotroller 객체임을 상속을 통해 구현하는게 아니라 POJO 스타일로 구현하게 된 것;
	// @Controller 어노테이션 통해 스프링컨테이너가 자동으로 객체 생성 및, Controller 객체임을 인식한다.

	@RequestMapping("/insertBoard.do") // auto HandlerMapping
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 등록 처리");
		boardDAO.insertBoard(vo);

		return "redirect:getBoardList.do";
	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 수정 처리");
		boardDAO.updateBoard(vo);

		return "redirect:getBoardList.do";
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 삭제 처리");
		boardDAO.deleteBoard(vo);

		return "redirect:getBoardList.do";
	}

	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {

		System.out.println("글 상세 조회 처리");

		mav.addObject("board", boardDAO.getBoard(vo)); // Model 정보 저장
		mav.setViewName("getBoard.jsp"); // View 정보 저장-viewResolver의 suffix 처리 안됨

		return mav;
	}

	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {

		System.out.println("글 목록 검색 처리");

		mav.addObject("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
		mav.setViewName("getBoardList.jsp"); // View 정보 저장-viewResolver의 suffix 처리 안됨

		return mav;
	}

}
