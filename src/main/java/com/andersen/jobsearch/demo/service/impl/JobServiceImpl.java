package com.andersen.jobsearch.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.repository.EmployerRepository;
import com.andersen.jobsearch.demo.repository.JobRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.JobService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService
{
	private JobRepository jobRepository;
	private EmployerRepository employerRepository;
	private UserRepository userRepository;
	private CompanyRepository companyRepository;
	
	@Autowired
	public JobServiceImpl(JobRepository jobRepository, EmployerRepository employerRepository,
			UserRepository userRepository, CompanyRepository companyRepository)
	{
		this.jobRepository = jobRepository;
		this.employerRepository = employerRepository;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
	}
	 
	@Override
	public Job saveJob(JobDto jobDto, String employerUsername) 
	{
		User user = userRepository.findByUsername(employerUsername).
				orElseThrow(() -> new IllegalArgumentException("The employer with username " + employerUsername + " does not exist."));
		
		Employer employer = employerRepository.findByUser(user);
		Job job =  JobDto.fromDto(jobDto);
		job.setEmployer(employer);
		job.setCompany(employer.getCompany());
		
		return jobRepository.save(job);
	}
	
	@Override
	public Job modifyJob(JobDto jobDto, Long jobId) 
	{
		Job jobFromDb = jobRepository.getOne(jobId);
		
		jobFromDb.setJobTitle(jobDto.getJobTitle());
		jobFromDb.setDescription(jobDto.getDescription());
		jobFromDb.setIndustry(jobDto.getIndustry());
		jobFromDb.setLocation(jobDto.getLocation());
		jobFromDb.setSkills(jobDto.getSkills());
		jobFromDb.setSalary(jobDto.getSalary());
		jobFromDb.setEmploymentType(jobDto.getEmploymentType());
		
		return jobRepository.save(jobFromDb);
	}
	
	@Override
	public void deleteJob(Long jobId) 
	{
		if(jobRepository.existsById(jobId))
			jobRepository.deleteById(jobId);
	}

	@Override
	public List<Job> getAllJobs() 
	{
		return jobRepository.findAll();
	}
	
	public List<JobDto> getListOfJobsDto(List<Job> jobs)
	{
		List<JobDto> jobsDto = new ArrayList<>();
		jobs.forEach(job -> jobsDto.add(JobDto.toDto(job)));
		return jobsDto;
	}

	@Override
	public List<JobDto> findJobsByEmployer(String employerUsername) 
	{
		User user = userRepository.findByUsername(employerUsername).
				orElseThrow(() -> new IllegalArgumentException("The employer with username " + employerUsername + " does not exist."));
		
		Employer employer = employerRepository.findByUser(user);
		return getListOfJobsDto(jobRepository.findByEmployer(employer));
	}

	@Override
	public List<JobDto> findJobsByTitle(String jobTitle) 
	{
		return getListOfJobsDto(jobRepository.findByJobTitleContainingIgnoreCase(jobTitle));
	}

	@Override
	public List<JobDto> findJobsByLocation(String location) 
	{
		return getListOfJobsDto(jobRepository.findByLocation(location));
	}

	@Override
	public List<JobDto> findJobsByCompany(String companyName) 
	{
		Company company = companyRepository.findByName(companyName);
		return getListOfJobsDto(jobRepository.findByCompany(company));
	}

	@Override
	public List<JobDto> findJobsByJobTitleAndLocation(String jobTitle, String location) 
	{
		return getListOfJobsDto(jobRepository.findByJobTitleAndLocation(jobTitle, location));
	}

	@Override
	public List<JobDto> findJobsByEmploymentType(String type) 
	{
		return getListOfJobsDto(jobRepository.findByEmploymentType(type));
	}
}
