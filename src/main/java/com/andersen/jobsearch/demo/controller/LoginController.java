package com.andersen.jobsearch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.andersen.jobsearch.demo.controller.command.AuthenticationFormCommand;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.security.filters.JwtAuthenticationFilter;
import com.andersen.jobsearch.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/auth/")
public class LoginController
{
	@GetMapping("/login")
	public ModelAndView login()
	{
		return new ModelAndView("login");
	}
	
	@GetMapping(value = {"/logout"})
    public ModelAndView logout() 
	{
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ModelAndView("login");
    }
}
