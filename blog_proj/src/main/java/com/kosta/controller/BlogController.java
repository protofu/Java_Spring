package com.kosta.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.entity.Article;
import com.kosta.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;
	
	// save page
	@GetMapping("/add")
	public String addPage() {
		return "form";
	}
	
	// save func
	@PostMapping("/add")
	public String addArticle(Article article) {
		blogService.save(article);
		return "redirect:/list";
	}
	
	// list
	@GetMapping("/list")
	public String listPage(Model model) {
		List<Article> articleList = blogService.findAll();
		model.addAttribute("list", articleList);
		return "list";
	}
	
	// findById
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable("id") Long id, Model model) {
		try {
			Article article = blogService.findById(id);
			model.addAttribute("article", article);
			return "detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}
	
	// delete
	@DeleteMapping("/delete/{id}")
	public String deleteArticle(@PathVariable("id") Long id, Model model) {
		try {
			blogService.deleteById(id);
			return "redirect:/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}
}
