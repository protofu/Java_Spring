package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.dto.UserDTO;
import com.kosta.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	
	@RequestMapping(value = {"", "/", "/list"})
	public ModelAndView showUserList() {
		ModelAndView mav = new ModelAndView("user/list");
		try {
			List<UserDTO> allUserList = us.getAllUserList();
			mav.addObject("list", allUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@GetMapping("/add")
	public String showUserAddForm() {
		return "user/add";
	}
	
	@PostMapping("/add")
	public String userAdd(UserDTO userDTO) {
		try {
			us.addUser(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user";
	}
	
	@DeleteMapping("/delete")
	public String userDelete(@RequestParam("id") int id) {
		try {
			us.removeUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user";
	}
	

	
}
