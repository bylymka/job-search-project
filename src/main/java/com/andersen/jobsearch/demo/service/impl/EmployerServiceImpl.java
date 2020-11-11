package com.andersen.jobsearch.demo.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.jobsearch.demo.dto.EmployerRegistrationDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.repository.EmployerRepository;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.EmployerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class EmployerServiceImpl implements EmployerService
{
	private EmployerRepository employerRepository;
	private UserRepository userRepository;
	private CompanyRepository companyRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public EmployerServiceImpl(EmployerRepository employerRepository, UserRepository userRepository,
			CompanyRepository companyRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository)
	{
		this.employerRepository = employerRepository;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public Employer findById(Long id)
	{
		return employerRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The employer with id " + id + " does not exist."));
	}

	@Override
	public Employer registerEmployer(EmployerRegistrationDto employerDto) throws EntityAlreadyExistAuthenticationException
	{
		if(userRepository.existsUserByUsername(employerDto.getUsername()))
		{
			throw new EntityAlreadyExistAuthenticationException(
					"User with username " + employerDto.getUsername() + " already exists.");
		}
		
		Employer employer = EmployerRegistrationDto.fromDto(employerDto);
				
		Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(3)); // 3 - EPLOYER_ROLE
        log.info("Added role for user with username: " + employerDto.getUsername());
        employer.getUser().setRoles(roles);
        employer.getUser().setPassword(bCryptPasswordEncoder.encode(employerDto.getPassword()));
        
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
