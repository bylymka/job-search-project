package com.andersen.jobsearch.demo.service;

import com.andersen.jobsearch.demo.dto.UserDto;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.exception.PasswordException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface UserService
{
	User findById(Long id);
	
	User findByUsername(String username);
	
	User modifyUser(UserDto userDto, Long userId);
	
	void updatePassword(String username, String oldPassword, String newPassword) throws PasswordException;
	
	User saveUser(User user);
	
	List<User> findAll();
	
	void setBan(Long userId, boolean ban);
	
	boolean isUserBanned(Long userId);
}
