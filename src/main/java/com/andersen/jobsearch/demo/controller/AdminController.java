package com.andersen.jobsearch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.andersen.jobsearch.demo.service.EmployeeService;
import com.andersen.jobsearch.demo.service.JobService;
import com.andersen.jobsearch.demo.service.ResumeService;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController
{
	private ResumeService resumeService;
	private EmployeeService employeeService;
	private JobService jobService;
	private UserService userService;
	
	@Autowired
	public AdminController(ResumeService resumeService, EmployeeService employeeService,
			JobService jobService, UserService userService)
	{
		this.resumeService = resumeService;
		this.employeeService = employeeService;
		this.jobService = jobService;
		this.userService = userService;
	}
	
	@GetMapping("/admin/dashboad")
	public String getAllUsers()
	{
		return "admin/get-users";
	}
	
	@PostMapping("/admin/ban-user")
	public String banUser()
	{
		return "admin/ban-user";
	}
}
