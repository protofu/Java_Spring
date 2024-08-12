package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.BoardDTO;

@Mapper
// DAO
public interface BoardMapper {
	List<BoardDTO> selectBoardList() throws Exception;
}
