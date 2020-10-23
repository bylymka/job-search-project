package com.andersen.jobsearch.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface EmployeeService
{
	Optional<Employee> findById(Long id);
	
	Employee saveEmployee(Employee employee) throws EntityAlreadyExistAuthenticationException;
	
	Employee modifyEmployee(Employee employee);
}
