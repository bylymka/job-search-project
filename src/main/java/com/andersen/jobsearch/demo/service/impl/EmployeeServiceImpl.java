package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.EmployeeRepository;
import com.andersen.jobsearch.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Optional<Employee> findById(Long id) 
	{
		return employeeRepository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) throws EntityAlreadyExistAuthenticationException
	{
		return employeeRepository.save(employee);
	}

	@Override
	public Employee modifyEmployee(Employee employee) 
	{
		Optional<Employee> employeeFromDb = employeeRepository.findById(employee.getId());
		employeeFromDb.get().setId(employee.getId());
		employeeFromDb.get().setUser(employee.getUser());
		employeeFromDb.get().setResumes(employee.getResumes());
		
		employeeRepository.save(employeeFromDb.get());
		return employeeFromDb.get();
	}
}
;