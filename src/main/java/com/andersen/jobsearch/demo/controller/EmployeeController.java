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

import com.andersen.jobsearch.demo.dto.ResumeDto;
import com.andersen.jobsearch.demo.dto.UserDto;
import com.andersen.jobsearch.demo.dto.EmployeeDto;
import com.andersen.jobsearch.demo.dto.FullJobDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.service.CompanyService;
import com.andersen.jobsearch.demo.service.EmployeeService;
import com.andersen.jobsearch.demo.service.JobService;
import com.andersen.jobsearch.demo.service.ResumeService;
import com.andersen.jobsearch.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController
{
	private ResumeService resumeService;
	private EmployeeService employeeService;
	private JobService jobService;
	private UserService userService;
	
	@Autowired
	public EmployeeController(ResumeService resumeService, EmployeeService employeeService,
			JobService jobService, UserService userService)
	{
		this.resumeService = resumeService;
		this.employeeService = employeeService;
		this.jobService = jobService;
		this.userService = userService;
	}
	
	@GetMapping("/employee/dashboard")
	public String getPageEmployeeDashboard(Model model)
	{
		model.addAttribute("proffesion", new String());
		model.addAttribute("city", new String());
		return "employee/employee-dashboard";
	}
	
	@GetMapping("/employee/find-jobs")
	public String getPageFindJobs(@RequestParam(value="proffesion",required=false) String proffesion,
			@RequestParam(value="city",required=false) String city, Model model)
	{	
		List<FullJobDto> jobs = null;
		
		if((city == null || city.isEmpty()) && (proffesion != null && !(proffesion.isEmpty())))
		{
			jobs = FullJobDto.getListOfJobsDto(jobService.findJobsByTitle(proffesion));
			model.addAttribute("jobs", jobs);
			return "employee/find-jobs";
		}
		
		if((city != null && !(city.isEmpty())) && (proffesion != null && !(city.isEmpty())))
		{
			jobs = FullJobDto.getListOfJobsDto(jobService.findJobsByProffesionAndCity(proffesion, city));
			model.addAttribute("jobs", jobs);
			return "employee/find-jobs";
		}
		
		return "employee/employee-dashboard";
	}
	
	@GetMapping("/employee/post-resume")
	public String getPagePostResume(Model model)
	{
		model.addAttribute("resumeDto", new ResumeDto());
		return "employee/post-resume";
	}
	
	@PostMapping("/employee/post-resume")
	public String postResume(@ModelAttribute("resumeDto") @Valid ResumeDto resumeDto,
			Model model, BindingResult bindingResult)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(bindingResult.hasErrors())
		{
			log.info("Resume: " + resumeDto + "was not posted. Binding result has errors");
			return "employee/post-resume";
		}
		
		resumeService.saveResume(resumeDto, username);
		return "redirect:/employee/get-resumes";
	}
	
	@GetMapping("/employee/get-resumes")
	public String getEmployerJobs(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ResumeDto> resumes = ResumeDto.getListOfResumeDto(resumeService.findResumesByEmployee(username));
		model.addAttribute("resumes", resumes);
		return "employee/employee-resumes";
	}
	
	@GetMapping("/employee/account")
	public String getInfoAboutEmployee(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = employeeService.findByUsername(username);
		EmployeeDto employeeDto = EmployeeDto.toDto(employee);
		model.addAttribute("employeeDto", employeeDto);
		return "employee/employee-account";
	}
	
	@GetMapping("/employee/account/edit/user")
	public String getPageEditUserInfo(Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = employeeService.findByUsername(username);
		model.addAttribute("userDto", UserDto.toDto(employee.getUser()));
		model.addAttribute("userId", employee.getUser().getId());
		return "employee/edit-employee";
	}
	
	
	@PostMapping("/employee/account/edit/user/{id}")
	public String editUserInfo(@PathVariable("id") Long userId, @ModelAttribute("userDto")
		@Valid UserDto userDto, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
		{
			log.info("User with id: " + userId + "was not edited. Binding result has errors");
			return  "redirect:/employee/account/edit/user";
		}
		
		User user = userService.findById(userId);
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNum(user.getPhoneNum());

		userService.saveUser(user);

		return  "redirect:/employee/account";
	}
	
	@GetMapping("/employee/get-jobs/apply-for-job/{jobId}")
	public String apply(@PathVariable("jobId") Long jobId, Model model)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("resumes", resumeService.findResumesByEmployee(username));
		model.addAttribute("jobId", jobId);
		model.addAttribute("resumeId", new String());
		return "employee/apply-for-job";
	}
	
	@PostMapping("/employee/get-jobs/apply-for-job/{jobId}")
	public String apply(@RequestParam(name = "resumeId")Long resumeId, @PathVariable(name = "jobId") Long jobId)
	{
		jobService.apply(resumeId, jobId);
		return "redirect:/employee/dashboard";
	}
}
