package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.BoardDTO;
import com.board.mapper.BoardMapper;

@Service // 비즈니스 로직을 처리하는 서비스 클래스임을 나타내는 어노테이션
public class BoardServiceImpl implements BoardService {
	@Autowired // Mapper 자동 주입
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		// 비즈니스 로직 (리스트 가져오기)
		List<BoardDTO> boardList = boardMapper.selectBoardList(); 
		return boardList;
	}

	
}
