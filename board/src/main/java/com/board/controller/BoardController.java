package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardDTO;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컨트롤러 지정 어노테이션
@RequestMapping("/board")
public class BoardController {
	@Autowired // 서비스 빈(Bean) 자동 주입
	private BoardService bs;
	
	@RequestMapping("/list") // 요청에 맞는 주소 지정
	public ModelAndView boardList() throws Exception {
		String data = "로그 출력 연습";
		log.trace("trace : {}", data);
		log.debug("debug : {}", data);
		log.info("info : {}", data);
		log.warn("warn : {}", data);
		log.error("error : {}", data);
		
		// src/main/resource/templates/board/list.html으로 화면 지정
		ModelAndView mv = new ModelAndView("board/list");
		// 비지니스 로직 수행
		List<BoardDTO> boardList = bs.selectBoardList();
		mv.addObject("list", boardList);
		return mv;
	}
	
	@GetMapping("/insert")
	public String boardInsertView() throws Exception {
		log.warn("글쓰기 페이지로 이동");
		return "board/write";
	}
	
	@PostMapping("/insert")
	public String boardInsert(BoardDTO boardDTO) throws Exception {
		// 글쓰기 비지니스 로직
		bs.insertBoard(boardDTO);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/detail")
	public ModelAndView boardDetail(@RequestParam("id") int id) throws Exception {
		ModelAndView mv = new ModelAndView("board/detail");
		BoardDTO boardDTO = bs.selectBoardById(id);
		mv.addObject("board", boardDTO);
		return mv;
	}
	
	@PostMapping("/update")
	public String boardUpdate(BoardDTO boardDTO) throws Exception {
		bs.updateBoard(boardDTO);
		return "redirect:/board/list";
	}
	
	@PostMapping("/delete")
	public String boardDelete(@RequestParam("id") int id) throws Exception {
		bs.deleteBoard(id);
		return "redirect:/board/list";
	}
}
