package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.AvailableRoles;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserServiceImpl implements UserService
{
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
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
				orElseThrow(() -> new IllegalArgumentException("The user with username " + username + " does not exist."));
	}

	@Override
	public User registerUser(User user) throws EntityAlreadyExistAuthenticationException 
	{
		if(userRepository.existsUserByUsername(user.getUsername()))
			throw new EntityAlreadyExistAuthenticationException(
					"User with username " + user.getUsername() + " already exists.");
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsersByRole(AvailableRoles role) 
	{
		return roleRepository.findByRole(role);
	}

	@Override
	public User modifyUser(User user)
	{
		User userFromDb = userRepository.findById(user.getId())
				.orElseThrow(() -> new IllegalArgumentException("The user with id " + user.getId() + " does not exist."));
						
		userFromDb.setId(user.getId());
		//userFromDb.setRoles(user.getRoles());
		userFromDb.setPassword(user.getPassword());
		userFromDb.setUsername(user.getUsername());
		userRepository.save(userFromDb);
		
		return userFromDb;
	}
}
