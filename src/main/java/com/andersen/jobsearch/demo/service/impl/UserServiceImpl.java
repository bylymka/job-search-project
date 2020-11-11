package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserServiceImpl implements UserService
{	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) 
	{
	  this.userRepository = userRepository;
	  this.roleRepository = roleRepository;
	}
	  
	public UserServiceImpl() 
	{
	  
	}
	 
	@Override
	public User findById(Long id) 
	{
		return userRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The user with id " + id + " does not exist."));
	}

	@Override
	public User findByUsername(String username) 
	{
		return userRepository.findByUsername(username).
				orElseThrow(() -> new IllegalArgumentException("The user with id " + username + " does not exist."));
	}

	/*@Override
	public List<User> findAllUsersByRole(String role) 
	{
		return roleRepository.findByRole(role);
	}*/
}
