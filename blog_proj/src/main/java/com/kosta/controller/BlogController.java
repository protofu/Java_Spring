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
		return "redirect:/blog/list";
	}
	
	// list & search
	@GetMapping("/list")
	public String listPage(@RequestParam(required=false, name="keyword") String keyword, @RequestParam(required=false, name="order") String order, Model model) {
		List<Article> articleList;
		if (keyword != null) {
			articleList = blogService.searchAndOrder(keyword, order);
		} else {
			articleList = blogService.findAll();
		}
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
			return "redirect:/blog/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}
	
	// modify
	@GetMapping("/modify/{id}")
	public String modifyPage(@PathVariable("id") Long id, Model model) {
		try {
			Article article = blogService.findById(id);
			model.addAttribute("article", article);
			return "form";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}
	
	// modify
	@PatchMapping("/modify")
	public String modifyArticle(Article article, Model model) {
		try {
			blogService.update(article);
			return "redirect:/blog/detail/" + article.getId();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}

}
