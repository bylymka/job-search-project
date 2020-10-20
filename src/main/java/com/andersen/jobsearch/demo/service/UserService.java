package com.andersen.jobsearch.demo.service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface UserService
{
	User findById(Long id);
	
	User findByUsername(String username);
	
	User saveUser(User user) throws EntityAlreadyExistAuthenticationException;
	
	List<User> findAllUsersByRole(Role role);
	
	User modifyUser(User user);
}
