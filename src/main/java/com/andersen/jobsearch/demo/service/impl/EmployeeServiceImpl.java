package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.jobsearch.demo.dto.EmployeeRegistrationDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.EmployeeRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService
{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository)
	{
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Employee findById(Long id) 
	{
		return employeeRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The employee with id " + id + " does not exist."));
	}

	@Override
	@Transactional
	public Employee registerEmployee(EmployeeRegistrationDto employeeDto) throws EntityAlreadyExistAuthenticationException
	{
		if(Optional.ofNullable(userRepository.findByUsername(employeeDto.getUsername())) == null)
		{
			throw new EntityAlreadyExistAuthenticationException(
					"User with username " + employeeDto.getUsername() + " already exists.");
		}
		
		Employee employee = EmployeeRegistrationDto.fromDto(employeeDto);
		
		User user = userRepository.save(employee.getUser());
		employee.getUser().setId(user.getId());
		log.info("User with username: " + user.getUsername() + "was registered");
		return employeeRepository.save(employee);
	}

	/*@Override
	public Employee modifyEmployee(long id, EmployeeRegistrationDto employeeDto) 
	{
		Employee employeeFromDb = employeeRepository.getOne(id);
		Employee updatedEmployee = EmployeeRegistrationDto.fromDto(employeeDto);
		
		employeeFromDb.setUser(updatedEmployee.getUser());
		
		employeeRepository.save(employeeFromDb);
		return employeeFromDb;
	}*/
}