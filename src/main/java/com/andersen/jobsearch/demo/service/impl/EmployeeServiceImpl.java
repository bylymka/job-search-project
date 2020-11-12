package com.andersen.jobsearch.demo.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.jobsearch.demo.dto.EmployeeRegistrationDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.EmployeeRepository;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
	private EmployeeRepository employeeRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository,
			RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Employee findById(Long id) 
	{
		return employeeRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The employee with id " + id + " does not exist."));
	}

	@Override
	public Employee registerEmployee(EmployeeRegistrationDto employeeDto) throws EntityAlreadyExistAuthenticationException
	{
		if(userRepository.existsUserByUsername(employeeDto.getUsername()))
		{
			throw new EntityAlreadyExistAuthenticationException(
					"User with username " + employeeDto.getUsername() + " already exists.");
		}
		
		Employee employee = EmployeeRegistrationDto.fromDto(employeeDto);
		
		Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(2)); // 2 - EPLOYEE_ROLE
        log.info("Added role for user with username: " + employeeDto.getUsername());
        employee.getUser().setRoles(roles);
        employee.getUser().setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        
		User user = userRepository.save(employee.getUser());
		employee.getUser().setId(user.getId());
		log.info("User with username: " + user.getUsername() + "was registered");
		return employeeRepository.save(employee);
	}
}