package com.andersen.jobsearch.demo.service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.entity.UserRole;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface UserService
{
	User findById(Long id);
	
	Optional<User> findByUsername(String username);
	
	//List<User> findAllUsersByRole(UserRole role);
}
