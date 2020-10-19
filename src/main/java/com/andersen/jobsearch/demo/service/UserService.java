package com.andersen.jobsearch.demo.service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface UserService
{
	Optional<User> findById(Long id);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> saveUser(User user);
	
	List<User> findAllUsersByRole(Role role);
	
	Optional<User> modifyUser(User user);
}
