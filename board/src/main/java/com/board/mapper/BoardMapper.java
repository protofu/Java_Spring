package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.BoardDTO;

@Mapper // MyBatis 매퍼 인터페이스임을 선언 (DAO)
public interface BoardMapper {
	List<BoardDTO> selectBoardList() throws Exception;
}
