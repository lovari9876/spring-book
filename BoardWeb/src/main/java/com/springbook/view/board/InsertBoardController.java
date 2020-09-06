package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 등록 처리");

//		BoardDAO boardDAO = new BoardDAO(); // argument로 넣으면 컨테이너가 자동 생성해준다.
//		직접 객체를 생성할 필요가 없다.
		boardDAO.insertBoard(vo);
	
		return "redirect:getBoardList.do";
	}

}
