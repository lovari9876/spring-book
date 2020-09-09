package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

// Annotation 사용하여 간단해진 컨트롤러들을 게시판, 회원관리 두 가지로 각각 한 데 묶어보자

@Controller
@SessionAttributes("board")
public class BoardController {
	// implements Controller 삭제; 이제 Annotation 쓴다.
	// handleRequest()를 Overriding할 필요도, response 객체를 파라미터로 받을 필요도 없어졌다.
	// return type ModelAndView를 계속 쓸 필요도 없어졌다!
	// Cotroller 객체임을 상속을 통해 구현하는게 아니라 POJO 스타일로 구현하게 된 것;
	// @Controller 어노테이션 통해 스프링컨테이너가 자동으로 객체 생성 및, Controller 객체임을 인식한다.

	@Autowired
	private BoardService boardService;

	// 새 글 쓰기
	@RequestMapping("/insertBoard.do") // auto HandlerMapping
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) throws IOException {

		System.out.println("글 등록 처리");
		
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("E:/dev/springQuickStart/" + fileName));
		}
		
		boardService.insertBoard(vo);

		return "redirect:getBoardList.do";
	}

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 수정 처리");
		System.out.println("작성자: " + vo.getWriter());
		boardService.updateBoard(vo);

		return "redirect:getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {

		System.out.println("글 삭제 처리");
		boardService.deleteBoard(vo);

		return "redirect:getBoardList.do";
	}

	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {

		System.out.println("글 상세 조회 처리");

		model.addAttribute("board", boardService.getBoard(vo)); // Model 정보 저장

		return "getBoard.jsp";
	}

	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");

		return conditionMap;
	}

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {

		System.out.println("글 목록 검색 처리");
		System.out.println("키워드: " + vo.getSearchKeyword());

		// Null check
		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");

		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장

		return "getBoardList.jsp";
	}

//	@RequestMapping("/getBoardList.do")
//	public String getBoardList(
//			@RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
//			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
//			BoardDAO boardDAO, Model model) {
//
//		System.out.println("글 목록 검색 처리");
//		System.out.println("검색 조건: " + condition);
//		System.out.println("검색 단어: " + keyword);
//
////		model.addAttribute("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
//
//		return "getBoardList.jsp";
//	}

}
