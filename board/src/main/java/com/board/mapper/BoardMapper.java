package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.BoardDTO;

@Mapper // MyBatis 매퍼 인터페이스임을 선언 (DAO)
public interface BoardMapper {
	List<BoardDTO> selectBoardList() throws Exception;

	void insertBoard(BoardDTO boardDTO) throws Exception;

	BoardDTO selectBoardById(int id) throws Exception;

	void updateBoard(BoardDTO boardDTO) throws Exception;

	void updateHit(int id) throws Exception;

	void deleteBoard(int id) throws Exception;

	
}
