package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.dto.OlympicDTO;
import com.kosta.service.OlympicService;

@Controller
@RequestMapping("/olympic")
public class OlympicController {
	@Autowired
	private OlympicService os;
	// 올림픽 순위 화면
	public ModelAndView showRankList() throws Exception {
		ModelAndView mav = new ModelAndView("/");
		List<OlympicDTO> rankList = os.getRank();
		return mav;
	}
	// 올림픽 나라 추가
	
	// 나라 삭제
	
	// 나라 수정
	
	// 나라 상세 보기
	
	// 파일 이미지 다운로드
}
