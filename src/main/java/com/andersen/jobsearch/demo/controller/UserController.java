package com.andersen.jobsearch.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.service.UserService;
import com.andersen.jobsearch.demo.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/users")
@Slf4j
public class UserController
{
		
}
