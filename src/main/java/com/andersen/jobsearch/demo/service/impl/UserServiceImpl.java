package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.repository.UserRolesRepository;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserServiceImpl implements UserService
{	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository) 
	{
	  this.userRepository = userRepository;
	  this.userRolesRepository = userRolesRepository;
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
	public List<User> findAllUsersByRole(Role role) 
	{
		return userRolesRepository.findUserByRole(role);
	}

	@Override
	public User modifyUser(User user)
	{
		User userFromDb = userRepository.findById(user.getId())
				.orElseThrow(() -> new IllegalArgumentException("The user with id " + user.getId() + " does not exist."));
						
		userFromDb.setId(user.getId());
		userFromDb.setPassword(user.getPassword());
		userFromDb.setUsername(user.getUsername());
		userFromDb.setFirstName(user.getFirstName());
		userFromDb.setLastName(user.getLastName());
		userFromDb.setEmail(user.getEmail());
		userFromDb.setPhoneNum(user.getPhoneNum());
		
		userRepository.save(userFromDb);
		
		return userFromDb;
	}
}
