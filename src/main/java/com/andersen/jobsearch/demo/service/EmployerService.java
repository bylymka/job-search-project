package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.EmployerRegistrationDto;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface EmployerService
{
	Employer findById(Long id);
	
	Employer registerEmployer(EmployerRegistrationDto employerDto) throws EntityAlreadyExistAuthenticationException;
}
