package com.kosta.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.entity.Article;
import com.kosta.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog/*")
public class BlogController {
	private final BlogService userService;
	
	// 전체 회원 리스트 검색 및 정렬
	@GetMapping("/list")
	public String getUserList(@RequestParam(required=false, name="keyword") String keyword, @RequestParam(required=false, name="order") String order, Model model) {
		List<Article> userList;
		if (keyword != null) {
			userList = userService.searchAndOrder(keyword, order);
		} else {
			userList = userService.findAll();
		}
		model.addAttribute("userlist", userList);
		return "ulist";
	}
	
	// 회원 추가 화면
	@GetMapping("/add")
	public String addForm() {
		return "form";
	}
	
	// 회원 추가 동작
	@PostMapping("/add")
	public String addUser(Article user) {
		userService.save(user);
		return "redirect:/blog/list";
	}
	
	// 회원 디테일 화면
	@GetMapping("/detail/{id}")
	public String detailUser(@PathVariable("id") Long id, Model model) throws Exception {
		Article user = userService.findById(id);
		model.addAttribute(user);
		return "udetail";
	}
	
	// 회원 삭제
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) throws Exception {
		userService.deleteById(id);
		return "redirect:/blog/list";
	}

	// 회원 수정 화면
	@GetMapping("/modify/{id}")
	public String modifyForm(@PathVariable("id") Long id, Model model) throws Exception {
		Article user = userService.findById(id);
		model.addAttribute("user", user);
		return "form";
	}

	// 회원 수정 동작
	@PatchMapping("/modify")
	public String modifyUser(Article user) throws Exception {
		userService.update(user);
		return "redirect:/blog/detail/" + user.getId();
	}
}
