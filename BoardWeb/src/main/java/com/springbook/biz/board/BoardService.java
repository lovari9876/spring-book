package com.springbook.biz.board;

import java.util.List;

public interface BoardService {

	// CRUD 기능의 메소드
	// 글 등록 create
	void insertBoard(BoardVO vo);

	// 글 수정 update
	void updateBoard(BoardVO vo);

	// 글 삭제 delete
	void deleteBoard(BoardVO vo);

	// 글 상세 조회 read
	BoardVO getBoard(BoardVO vo);

	// 글 목록 조회 read
	List<BoardVO> getBoardList(BoardVO vo);

}