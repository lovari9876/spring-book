
package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAOSpring2 boardDAO;
//	private LogAdvice log;

//	// log 객체 생성하는 생성자 => 공통 로직 분리안됨 
//	public BoardServiceImpl() {
//		log = new LogAdvice();
//	}

	@Override
	public void insertBoard(BoardVO vo) {
//		// ** 강제로 exception 일으켜서 afterThrowing advice 보려고~~
//		if(vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
//			// Constructs an IllegalArgumentException with the specified detail message.
//			// Construnctor argument로 구체적 String 예외 메시지 들어간다. 
//			// 나중에 getMassage()로 얻을 수 있음
//		}
		
		boardDAO.insertBoard(vo);
//		boardDAO.insertBoard(vo); // for tx rollback
		// client에서 인위적으로 seq=100 넣고서 중복되게 돌리면 에러뜨고 tx rollback되면서,
		// 아예 insertBoared(..) 메서드 자체가 아예 rollback된다. 
		// 결론적으로 100번글이 입력되었다가 commit 안되고 지워짐!
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}

}
