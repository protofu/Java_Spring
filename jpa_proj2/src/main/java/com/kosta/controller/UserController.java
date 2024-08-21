package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.entity.User;
import com.kosta.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService us;
	
	// 유저 목록 가져오기
	@GetMapping("/list")
	public ModelAndView userList() throws Exception {
		ModelAndView mav = new ModelAndView("user/userlist");
		List<User> users = us.getAll();
		mav.addObject("users", users);
		return mav;
	}
	
	// 유저 추가 화면
	@GetMapping("/add")
	public ModelAndView addForm() {
		ModelAndView mav = new ModelAndView("user/addform");
		return mav;
	}
	
	// 유저 추가 동작
	@PostMapping("/add")
	public String addForm(User user) {
		us.add(user);
		return "redirect:/list";
	}
	
	// 유저 삭제
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		us.deleteUser(id);
		return "redirect:/list";
	}
	
	// 유저 수정 화면
	@GetMapping("/update")
	public String updateForm(@RequestParam("id") int id, Model model) throws Exception {
		User user = us.getUserById(id);
		
		model.addAttribute("user", user);
		return "user/addform";
	}
	
	// 유저 수정
	@PostMapping("/update")
	public String updateForm(User user) throws Exception {
		us.modifyUser(user);
		return "redirect:/list";
	}
	
	// 유저 검색
	@GetMapping("/search")
	public String searchMember(@RequestParam("keyword") String keyword, Model model) throws Exception {
		List<User> userSearchResult = us.searchUser(keyword);
		model.addAttribute("users", userSearchResult);		
		return "user/userlist";
	}
}
