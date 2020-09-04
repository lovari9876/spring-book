package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

// * 방법 2. JdbcTemplate 객체를 <bean> 설정해주고, DI 해주기

// DAO(Data Access Object)
// 이 DAO 사용하려면 BoardServiceImple에서 @Autowired된 객체 타입 이걸로 바꿔줘야 함!
@Repository
public class BoardDAOSpring2 {

	// JDBC 관련 변수는 이제 필요없다. xml설정과 dbcp 이용으로 자동화했음

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// SQL 명령어들
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) "
			+ "values((select ifnull(max(seq), 0)+1 from board temp),?,?,?)";
	// 예 ) SELECT IFNULL(SALARY, 0) FROM EMPLOYEE_SALARY
	// 설명 - SALARY 값이 NULL 이면 0을, NULL 이 아니면 SALARY 값을 출력
	// ** MySql에서는 subquery에서 같은 테이블 쓸 때 alias 없이 쓸 수 없음
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	// CRUD 기능의 메소드 구현 ========================================
	// 글 등록 create
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// 글 수정 update
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

	// 글 삭제 delete
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}

	// 글 상세 조회 read
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		Object[] args = { vo.getSeq() }; // args말고 object 하나만 하고 싶어도, 메서드 인자로 배열이 들어가야해서 불가
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회 read
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

}
