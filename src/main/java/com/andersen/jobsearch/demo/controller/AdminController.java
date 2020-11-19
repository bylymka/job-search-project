package com.andersen.jobsearch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController
{
	private UserService userService;
	
	@Autowired
	public AdminController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/admin/dashboard")
	public String getAllUsers(Model model)
	{
		model.addAttribute("users", userService.findAll());
		model.addAttribute("ban", new Boolean(null));
		return "admin/admin-dashboard";
	}
	
	@GetMapping("/admin/dashboard/user")
	public String setBan(@RequestParam(name = "ban", required = false) boolean ban, @RequestParam("id") Long userId)
	{
		if(ban)
			userService.setBan(userId, true);
		if(!ban)
			userService.setBan(userId, false);
		
		return "redirect:/admin/dashboard";
	}
	
	@GetMapping("/admin/find-user")
	public String findUser(@RequestParam(name = "id", required = false) Long userId, 
			@RequestParam(name = "username", required = false) String username, Model model)
	{
		User user = null;
		
		if(userId != null && !(userId.toString().isEmpty()))
		{
			if(username == null || username.isEmpty())
				user = userService.findById(userId);
		}
		
		if(username != null && !(username.isEmpty()))
			user = userService.findByUsername(username);
		
		model.addAttribute("user", user);
		return "admin/find-user";
	}
}
