package com.andersen.jobsearch.demo.service;

import com.andersen.jobsearch.demo.dto.EmployeeRegistrationDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface EmployeeService
{
	Employee findById(Long id);
	
	Employee registerEmployee(EmployeeRegistrationDto employeeDto) throws EntityAlreadyExistAuthenticationException;
}
