package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardDTO;
import com.board.service.BoardService;

@Controller // 컨트롤러 지정 어노테이션
public class BoardController {
	@Autowired
	private BoardService bs;
	
	@RequestMapping("/board/list") // 요청에 맞는 주소 지정
	public ModelAndView boardList() throws Exception {
		// src/main/resource/templates/board/list
		ModelAndView mv = new ModelAndView("board/list");
		// 비지니스 로직 수행
		List<BoardDTO> boardList = bs.selectBoardList();
		mv.addObject("list", boardList);
		return mv;
	}
}
