package com.andersen.jobsearch.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersen.jobsearch.demo.dto.CompanyDto;
import com.andersen.jobsearch.demo.dto.EmployerDto;
import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.dto.ResumeWithInfoAboutEmployeeDto;
import com.andersen.jobsearch.demo.dto.UserDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.service.CompanyService;
import com.andersen.jobsearch.demo.service.EmployerService;
import com.andersen.jobsearch.demo.service.JobService;
import com.andersen.jobsearch.demo.service.ResumeService;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployerController
{
	private ResumeService resumeService;
	private EmployerService employerService;
	private JobService jobService;
	private UserService userService;
	private CompanyService companyService;
	
	@Autowired
	public EmployerController(ResumeService resumeService, EmployerService employerService,
			JobService jobService, UserService userService, CompanyService companyService)
	{
		this.resumeService = resumeService;
		this.employerService = employerService;
		this.jobService = jobService;
		this.userService = userService;
		this.companyService = companyService;
	}
	
	@GetMapping("/employer/dashboard")
	public String getPageEmployerDashboard(Model model)
	{
		model.addAttribute("proffesion", new String());
		model.addAttribute("city", new String());
		return "employer/employer-dashboard";
	}
	
	@GetMapping("/employer/find-resumes")
	public String getPageEmployerDashboard(@RequestParam(value="proffesion",required=false) String proffesion,
			@RequestParam(value="city",required=false) String city, Model model)
	{	
		List<ResumeWithInfoAboutEmployeeDto> resumes = null;
		
		if((city == null || city.isEmpty()) && (proffesion != null && !(proffesion.isEmpty())))
		{
			resumes = resumeService.findResumesByProffesion(proffesion);
			model.addAttribute("resumes", resumes);
			return "employer/find-resumes";
		}
		
		if((city != null && !(city.isEmpty())) && (proffesion != null && !(city.isEmpty())))
		{
			resumes = resumeService.findResumesByProffesionAndCity(proffesion, city);
			model.addAttribute("resumes", resumes);
			return "employer/find-resumes";
		}
		
		return "employer/employer-dashboard";
	}
	
	@GetMapping("/employer/post-job")
	public String getPagePostJob(Model model)
	{
		model.addAttribute("jobDto", new JobDto());
		return "employer/post-job";
	}
	
	@PostMapping("/employer/post-job")
	public String postJob(@ModelAttribute("jobDto") @Valid JobDto jobDto,
			Model model, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			log.info("Job: " + jobDto + "was not registered. Binding result has errors");
			return "employer/post-job";
		}
		
		jobService.saveJob(jobDto, SecurityContextHolder.getContext().getAuthentication().getName());
		return "employer/employer-dashboard";
	}
	
	
	@GetMapping("/employer/get-jobs")
	public String getEmployerJobs(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<JobDto> jobs = jobService.findJobsByEmployer(username);
		model.addAttribute("jobs", jobs);
		return "employer/employer-jobs";
	}
	
	@GetMapping("/employer/account")
	public String getInfoAboutEmployer(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employer employer = employerService.findEmployerByUsername(username);
		EmployerDto employerDto = EmployerDto.toDto(employer);
		model.addAttribute("employerDto", employerDto);
		return "employer/employer-account";
	}
	
	@GetMapping("/employer/account/edit/company")
	public String getPageEditCompanyInfo(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employer employer = employerService.findEmployerByUsername(username);
		model.addAttribute("companyDto", CompanyDto.toDto(employer.getCompany()));
		model.addAttribute("companyId", employer.getCompany().getId());
		return "employer/edit-company";
	}
	
	@GetMapping("/employer/account/edit/user")
	public String getPageEditUserInfo(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employer employer = employerService.findEmployerByUsername(username);
		model.addAttribute("userDto", UserDto.toDto(employer.getUser()));
		model.addAttribute("employerId", employer.getId());
		model.addAttribute("position", employer.getPosition());
		return "employer/edit-user";
	}
	
	@PostMapping("/employer/account/edit/company/{id}")
	public String editCompanyInfo(@PathVariable("id") Long companyId, @ModelAttribute("companyDto")
		@Valid CompanyDto companyDto, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			log.info("Company with id: " + companyId + "was not edited. Binding result has errors");
			return  "redirect:/employer/account/edit/company";
		}
		
		companyService.modifyCompany(companyDto, companyId);
		return  "redirect:/employer/account";
	}
	
	@PostMapping("/employer/account/edit/user/{id}")
	public String editUserInfo(@PathVariable("id") Long employerId, @ModelAttribute("userDto")
		@Valid UserDto userDto, @ModelAttribute("position") String position, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			log.info("Employer with id: " + employerId + "was not edited. Binding result has errors");
			return  "redirect:/employer/account/edit/user";
		}
		
		Employer employer = employerService.findById(employerId);
		User user = employer.getUser();
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNum(user.getPhoneNum());
		employer.setPosition(position);

		userService.saveUser(user);
		employerService.saveEmployer(employer);

		return  "redirect:/employer/account";
	}
}
