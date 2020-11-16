package com.andersen.jobsearch.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andersen.jobsearch.demo.dto.EmployeeDto;
import com.andersen.jobsearch.demo.dto.EmployerDto;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.service.CompanyService;
import com.andersen.jobsearch.demo.service.EmployeeService;
import com.andersen.jobsearch.demo.service.EmployerService;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegistrationController
{
	private EmployerService employerService;
	private EmployeeService employeeService;
	
	@Autowired
	public RegistrationController(EmployerService employerService,
			EmployeeService employeeService)
	{
		this.employerService = employerService;
		this.employeeService = employeeService;
	}

	@GetMapping("/sign-up/employee")
	public String getEmployeeRegistrationForm(Model model)
	{
		model.addAttribute("employeeDto", new EmployeeDto());
		return "employee/register-employee";
	}
	
	@GetMapping("/sign-up/employer")
	public String getEmployerRegistrationForm(Model model)
	{
		model.addAttribute("employerDto", new EmployerDto());
		return "employer/register-employer";
	}
	
	@PostMapping("/sign-up/employee")
	public String registerEmployee(@ModelAttribute("employeeDto") @Valid EmployeeDto dto,
			final BindingResult bindingResult, final Model model)
	{
		if(bindingResult.hasErrors())
		{
			log.info("Employee with username: " + dto.getUsername() + 
					"was not registered. Binding result has errors");
			return "employee/register-employee";
		}
		
		try
		{
			employeeService.registerEmployee(dto);
			log.info("Employee with username: " + dto.getUsername() + "was successfully registered.");
			return "login";
		}
		catch (EntityAlreadyExistAuthenticationException e)
		{
			log.info("Employee with username: " + dto.getUsername() + 
					"was not registered. User with such username already exists");
			return "employee/register-employee";
		}
	}
	
	@PostMapping("/sign-up/employer")
	public String registerEmployer(@ModelAttribute("employerDto") @Valid EmployerDto dto,
			final BindingResult bindingResult, final Model model)
	{
		if(bindingResult.hasErrors())
		{
			log.info("Employer with username: " + dto.getUsername() + 
					"was not registered. Binding result has errors");
			return "employer/register-employer";
		}
		
		try
		{
			employerService.registerEmployer(dto);
			log.info("Employer with username: " + dto.getUsername() + "was successfully registered.");
			return "login"; 
		}
		catch (EntityAlreadyExistAuthenticationException e)
		{
			log.info("Employer with username: " + dto.getUsername() + 
					"was not registered. User with such username already exists");
			return "employer/register-employer";
		}
	}
	
}
