package com.andersen.jobsearch.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.UserDto;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.PasswordException;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

	@Override
	public User modifyUser(UserDto userDto, Long userId) 
	{
		User userFromDb = findById(userId);
		userFromDb.setUsername(userDto.getUsername());
		userFromDb.setFirstName(userDto.getFirstName());
		userFromDb.setLastName(userDto.getLastName());
		userFromDb.setEmail(userDto.getEmail());
		userFromDb.setPhoneNum(userDto.getPhoneNum());
		return userRepository.save(userFromDb);
	}

	@Override
	public void updatePassword(String username, String oldPassword, String newPassword) throws PasswordException
	{
		String encodedOldPassword = bCryptPasswordEncoder.encode(oldPassword);
		User user = findByUsername(username);
		
		if(encodedOldPassword == user.getPassword())
		{
			user.setPassword(bCryptPasswordEncoder.encode(newPassword));
			userRepository.save(user);
		}
		else
		{
			log.info("User enetered wrong password. Password in DB and entered password are not equals");
			throw new PasswordException("Old password and new password are not equals");
		}	
	}
	
	@Override
	public User saveUser(User user) 
	{
		return userRepository.save(user);
	}
	
	@Override
	public List<User> findAll() 
	{
		return userRepository.findAll();
	}
	
	@Override
	public void setBan(Long userId, boolean ban)
	{
		User user = findById(userId);
		user.setIsBanned(ban);
		userRepository.save(user);
	}

	@Override
	public boolean isUserBanned(Long userId)
	{
		User user = findById(userId); 
		return user.getIsBanned();
	}
}
