package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.dto.OlympicDTO;
import com.kosta.dto.UserDTO;
import com.kosta.service.OlympicService;
import com.kosta.service.UserService;

@Controller
@RequestMapping("/olympic")
public class OlympicController {
	@Autowired
	private OlympicService os;
	@Autowired
	private UserService us;
	// 올림픽 순위 화면
	@RequestMapping(value = {"", "/", "/rank"})
	public ModelAndView showRankList() throws Exception {
		ModelAndView mav = new ModelAndView("olympic/rankList");
		List<OlympicDTO> rankList = os.getRank();
		mav.addObject("rank", rankList);
		
		return mav;
	}
	// 올림픽 나라 추가 화면
	@GetMapping("/add")
	public String showInternationalAddForm(Model model) throws Exception {
		List<UserDTO> allUserList = us.getAllUserList();
		model.addAttribute("users", allUserList);
		return "olympic/add";
	}
	
	//올림픽 나라 추가
	@PostMapping("/add")
	public String internationalAdd(OlympicDTO olympicDTO) throws Exception {
		os.addInternational(olympicDTO);
		return "redirect:/olympic";
	}
	
	// 나라 삭제
	@DeleteMapping("/delete")
	public String deleteInternational(@RequestParam("id")int id) throws Exception {
		os.deleteInternational(id);
		return "redirect:/olympic";
	}

	// 나라 상세 보기
	@RequestMapping("/detail")
	public ModelAndView showInternationalDetail(@RequestParam("id") int id) throws Exception {
		ModelAndView mav = new ModelAndView("olympic/detail");
		OlympicDTO interDTO = os.getInter(id);
		mav.addObject("inter", interDTO);
		UserDTO userDTO = us.getUser(interDTO.getPresidentId());
		mav.addObject("user", userDTO);
		return mav;
	}
	// 나라 수정 화면
	@GetMapping("/modify")
	public ModelAndView showInternationalModifyForm(@RequestParam("id") int id) throws Exception {
		ModelAndView mav = new ModelAndView("olympic/modify");
		OlympicDTO interDTO = os.getInter(id);
		mav.addObject("inter", interDTO);
		UserDTO userDTO = us.getUser(interDTO.getPresidentId());
		mav.addObject("user", userDTO);
		return mav;
	}
	// 나라 수정
	@PostMapping("/modify")
	public String modifyInternational(OlympicDTO olympicDTO) throws Exception {
		os.updateOlympic(olympicDTO);
		return "redirect:/olympic/detail="+olympicDTO.getId();
	}

	// 파일 이미지 다운로드
}
