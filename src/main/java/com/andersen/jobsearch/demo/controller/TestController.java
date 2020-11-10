package com.andersen.jobsearch.demo.controller;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.service.UserService;

@Controller
public class TestController
{
	private UserService userService;

	public TestController(UserService userService)
	{
		super();
		this.userService = userService;
	}
	
}
