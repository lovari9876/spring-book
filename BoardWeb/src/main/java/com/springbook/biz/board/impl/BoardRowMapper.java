package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springbook.biz.board.BoardVO;

// resultSet에서 sql query 실행결과 받아서 vo에 mapping해주는 RowMapper객체..
// 결국 테이블 하나당 하나의 RowMapper 클래스 필요.. 
public class BoardRowMapper implements RowMapper<BoardVO> {

	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		BoardVO board = new BoardVO();

		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));

		return board;
	}

}
