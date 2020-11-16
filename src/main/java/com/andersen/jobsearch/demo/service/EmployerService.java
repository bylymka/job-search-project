package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.EmployeeDto;
import com.andersen.jobsearch.demo.dto.EmployerDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface EmployerService
{
	Employer findById(Long id);
	Employer registerEmployer(EmployerDto employerDto) throws EntityAlreadyExistAuthenticationException;
	Employer findEmployerByUsername(String username);
	Employer findEmployerByUser(User user);
	Employer saveEmployer(Employer employer);
}
