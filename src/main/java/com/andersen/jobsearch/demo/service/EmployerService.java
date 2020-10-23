package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface EmployerService
{
	Optional<Employer> findById(Long id);
	
	Employer saveEmployer(Employer employer) throws EntityAlreadyExistAuthenticationException;
	
	Optional<Employer> modifyEmployer(Employer employer);
}
