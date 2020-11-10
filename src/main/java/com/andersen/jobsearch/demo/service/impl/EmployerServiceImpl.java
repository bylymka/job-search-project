package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.jobsearch.demo.dto.EmployeeRegistrationDto;
import com.andersen.jobsearch.demo.dto.EmployerRegistrationDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.repository.EmployerRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.EmployerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployerServiceImpl implements EmployerService
{
	EmployerRepository employerRepository;
	UserRepository userRepository;
	CompanyRepository companyRepository;

	public EmployerServiceImpl(EmployerRepository employerRepository, UserRepository userRepository,
			CompanyRepository companyRepository)
	{
		this.employerRepository = employerRepository;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
	}

	@Override
	public Employer findById(Long id)
	{
		return employerRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The employer with id " + id + " does not exist."));
	}

	@Override
	@Transactional
	public Employer registerEmployer(EmployerRegistrationDto employerDto) throws EntityAlreadyExistAuthenticationException
	{
		if(Optional.ofNullable(userRepository.findByUsername(employerDto.getUsername())) == null)
		{
			throw new EntityAlreadyExistAuthenticationException(
					"User with username " + employerDto.getUsername() + " already exists.");
		}
		
		Employer employer = EmployerRegistrationDto.fromDto(employerDto);
		
		User user = userRepository.save(employer.getUser());
		employer.getUser().setId(user.getId());
		log.info("In EmployerService: registerEmployer(). Registered user with username: " + employer.getUser().getUsername());
		
		Company company = companyRepository.save(employer.getCompany());
		employer.getCompany().setId(company.getId());
		log.info("In EmployerService: registerEmployer(). Registered company with name: " + employer.getCompany().getName());
		employer.setPosition(employerDto.getEmployerPosition());
		
		return employerRepository.save(employer);
	}

	/*@Override
	public Employer modifyEmployer(long id, EmployerRegistrationDto employerDto) 
	{
		Employer employerFromDb = employerRepository.getOne(id);
		Employer updatedEmployer = EmployerRegistrationDto.fromDto(employerDto);
		User updatedUser;
		Company updatedCompany()
		
		userRepository.save(entity)
		employerRepository.save(employerFromDb);
		return employerFromDb;
	}*/
}
