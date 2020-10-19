package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> findById(Long id) 
	{
		return Optional.of(userRepository.getOne(id));
	}

	@Override
	public Optional<User> findByUsername(String username) 
	{
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> saveUser(User user) 
	{
		return Optional.of(userRepository.save(user));
	}

	@Override
	public List<User> findAllUsersByRole(Role role) 
	{
		return userRepository.findByRole(role);
	}

	@Override
	public Optional<User> modifyUser(User user) 
	{
		Optional<User> userFromDb = userRepository.findById(user.getId());
		userFromDb.get().setId(user.getId());
		userFromDb.get().setRole(user.getRole());
		userFromDb.get().setPassword(user.getPassword());
		userFromDb.get().setUsername(user.getUsername());
		userRepository.save(userFromDb.get());
		return userFromDb;
	}
}
