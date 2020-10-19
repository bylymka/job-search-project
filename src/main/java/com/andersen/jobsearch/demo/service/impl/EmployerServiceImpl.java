package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.repository.EmployerRepository;
import com.andersen.jobsearch.demo.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService
{
	
	@Autowired
	EmployerRepository employerRepository;

	@Override
	public Optional<Employer> findById(Long id)
	{
		return employerRepository.findById(id);
	}

	@Override
	public Employer saveEmployer(Employer employer) 
	{
		return employerRepository.save(employer);
	}

	@Override
	public Optional<Employer> modifyEmployer(Employer employer) 
	{
		Optional<Employer> employerFromDb = employerRepository.findById(employer.getId());
		employerFromDb.get().setUser(employer.getUser());
		employerFromDb.get().setId(employer.getId());
		employerFromDb.get().setCompany(employer.getCompany());
		employerFromDb.get().setEmail(employer.getEmail());
		employerFromDb.get().setPosition(employer.getPosition());
		
		employerRepository.save(employerFromDb.get());
		return employerFromDb;
	}
}
