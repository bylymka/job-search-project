package com.andersen.jobsearch.demo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController
{
	@GetMapping("/")
	public String getHomePage(Model model, HttpServletRequest request)
	{
		return "index";
	}
}
