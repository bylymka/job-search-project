package com.andersen.jobsearch.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.service.impl.UserServiceImpl;

@RestController
public class TestController
{
	@Autowired
	UserServiceImpl userService;

	@GetMapping("/test-save-user")
	public String testSaveSource()
	{
		User user = new User("kkoop", Role.ADMIN, "pass");
		try
		{
			userService.saveUser(user);
			return user.toString();
		} 
		catch (EntityAlreadyExistAuthenticationException e)
		{
			return "Entity already exists";
		}
		catch (Exception e) 
		{
			return "Fail";
		}
	}
	
	@GetMapping("/test-get-user-list")
	public List<User> testGetUserList()
	{
		return userService.findAllUsersByRole(Role.ADMIN);
	}
}
