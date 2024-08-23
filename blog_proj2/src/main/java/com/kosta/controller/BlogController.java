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
import com.kosta.entity.User;
import com.kosta.service.BlogService;
import com.kosta.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog/*")
public class BlogController {
	private final BlogService articleService;
	private final UserService userService;
	
	// 전체 회원 리스트 검색 및 정렬
	@RequestMapping("/list")
	public String getarticleList(@RequestParam(required=false, name="keyword") String keyword, @RequestParam(required=false, name="order") String order, Model model) {
		List<Article> articleList;
		if (keyword != null) {
			articleList = articleService.searchAndOrder(keyword, order);
		} else {
			articleList = articleService.findAll();
			System.out.println(articleList);
		}
		model.addAttribute("articlelist", articleList);
		return "ulist";
	}
	
	// 회원 추가 화면
	@GetMapping("/add")
	public String addForm() {
		return "form";
	}
	
	// 회원 추가 동작
	@PostMapping("/add")
	public String addarticle(Article article) {
		articleService.save(article);
		return "redirect:/blog/list";
	}
	
	// 회원 디테일 화면
	@GetMapping("/detail/{id}")
	public String detailarticle(@PathVariable("id") Long id, Model model) throws Exception {
		Article article = articleService.findById(id);
		model.addAttribute(article);
		return "udetail";
	}
	
	// 회원 삭제
	@DeleteMapping("/delete/{id}")
	public String deletearticle(@PathVariable("id") Long id) throws Exception {
		articleService.deleteById(id);
		return "redirect:/blog/list";
	}

	// 회원 수정 화면
	@GetMapping("/modify/{id}")
	public String modifyForm(@PathVariable("id") Long id, Model model) throws Exception {
		Article article = articleService.findById(id);
		model.addAttribute("article", article);
		return "form";
	}

	// 회원 수정 동작
	@PatchMapping("/modify")
	public String modifyarticle(Article article) throws Exception {
		articleService.update(article);
		return "redirect:/blog/detail/" + article.getId();
	}
}
