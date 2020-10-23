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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.andersen.jobsearch.demo.controller.command.*;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.service.impl.CompanyServiceImpl;
import com.andersen.jobsearch.demo.service.impl.EmployeeServiceImpl;
import com.andersen.jobsearch.demo.service.impl.EmployerServiceImpl;
import com.andersen.jobsearch.demo.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/api/v1/registration/")
public class RegistrationController
{
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserServiceImpl userServiceImpl;
	private EmployerServiceImpl employerServiceImpl;
	private EmployeeServiceImpl employeeServiceImpl;
	private CompanyServiceImpl companyServiceImpl;
	
	@Autowired
	public RegistrationController(UserServiceImpl userServiceImpl,EmployerServiceImpl employerServiceImpl,
			EmployeeServiceImpl employeeServiceImpl, CompanyServiceImpl companyServiceImpl)
	{
		this.userServiceImpl = userServiceImpl;
		this.employerServiceImpl = employerServiceImpl;
		this.employeeServiceImpl = employeeServiceImpl;
		this.companyServiceImpl = companyServiceImpl;
	}

	@GetMapping("/sign-up/employee")
	public ModelAndView showRegistrationFormForEmployee(WebRequest request, Model model)
	{
		ModelAndView mav = new ModelAndView("sign-up/employee");
		model.addAttribute("employee", new UserRegistrationFormCommand());
		return mav;
	}
	
	@GetMapping("sign-up/employer")
	public ModelAndView showRegistrationFormForEmployer(WebRequest request, Model model)
	{
		ModelAndView mav = new ModelAndView("sign-up/employer");
		model.addAttribute("employer", new EmployerRegistrationFormCommand());
		return mav;
	}

	@PostMapping("/sign-up/emploee")
	public ModelAndView registerUserAccount(@ModelAttribute("employee") @Valid UserRegistrationFormCommand registrationForm,
			final BindingResult bindingResult, final Model model) throws EntityAlreadyExistAuthenticationException
	{
		ModelAndView mav = new ModelAndView("sign-up/employee");
		
		if(bindingResult.hasErrors())
			return mav;
		
		try
		{
			registrationForm.setPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
			User user = UserRegistrationFormCommand.getUserFromForm(registrationForm);
			userServiceImpl.registerUser(user);
		}
		catch (EntityAlreadyExistAuthenticationException e)
		{
			bindingResult.rejectValue("username", "registrationForm.username", e.getMessage());
			model.addAttribute("registrationForm", registrationForm);
			return mav;
		}
		
		return new ModelAndView("login");
	}
		
		
	@PostMapping("/sign-up/employer")
	public ModelAndView registerEmployer(@ModelAttribute("employer") @Valid EmployerRegistrationFormCommand registrationForm,
			final BindingResult bindingResult, final Model model) throws EntityAlreadyExistAuthenticationException
	{
		ModelAndView mav = new ModelAndView("sign-up/employee");
		
		if(bindingResult.hasErrors())
			return mav;
		
		try
		{
			UserRegistrationFormCommand userRegistrationForm = 
					((EmployerRegistrationFormCommand) registrationForm).getUserRegistrationFormCommand();
			
			CompanyRegistrationFormCommand companyRegistrationForm =
					((EmployerRegistrationFormCommand) registrationForm).getCompanyRegistrationFormCommand();
			
			userRegistrationForm.setPassword(bCryptPasswordEncoder.encode(userRegistrationForm.getPassword()));
			User user = UserRegistrationFormCommand.getUserFromForm(userRegistrationForm);
			user.setId(userServiceImpl.registerUser(user).getId());
			
			Company company = CompanyRegistrationFormCommand.getCompanyFromForm(companyRegistrationForm);
			company.setId(companyServiceImpl.saveCompany(company).getId());
			
			Employer employer = EmployerRegistrationFormCommand.getEmployerFromForm(registrationForm);	
			employerServiceImpl.saveEmployer(employer);
		}
		catch (EntityAlreadyExistAuthenticationException e)
		{
			bindingResult.rejectValue("username", "registrationForm.username", e.getMessage());
			model.addAttribute("registrationForm", registrationForm);
			return mav;
		}
		
		return new ModelAndView("login");
	}
}
