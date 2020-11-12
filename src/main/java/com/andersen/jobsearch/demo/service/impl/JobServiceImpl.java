package com.andersen.jobsearch.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.EmploymentType;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.User;
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
	
	@Autowired
	public JobServiceImpl(JobRepository jobRepository, EmployerRepository employerRepository, UserRepository userRepository)
	{
		this.jobRepository = jobRepository;
		this.employerRepository = employerRepository;
		this.userRepository = userRepository;
	}

	 
	@Override
	public Job saveJob(JobDto jobDto, String employerUsername) 
	{
		User user = userRepository.findByUsername(employerUsername).
				orElseThrow(() -> new IllegalArgumentException("The employer with username " + employerUsername + " does not exist."));
		
		log.info("The employer with username " + employerUsername + " does not exist.");
		
		Employer employer = employerRepository.getOne(user.getId());
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
		jobFromDb.setEmploymentType(EmploymentType.valueOf(jobDto.getEmploymentType().replace('-', '_').toUpperCase()));
		
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

	@Override
	public List<Job> findJobsByEmployer(Employer employer) 
	{
		return jobRepository.findByEmployer(employer);
	}

	@Override
	public List<Job> findJobsByTitle(String jobTitle) 
	{
		return jobRepository.findByJobTitleContainingIgnoreCase(jobTitle);
	}

	@Override
	public List<Job> findJobsByLocation(String location) 
	{
		return jobRepository.findByLocation(location);
	}

	@Override
	public List<Job> findJobsByCompany(Company company) 
	{
		return jobRepository.findByCompany(company);
	}

	@Override
	public List<Job> findJobsByJobTitleAndLocation(String jobTitle, String location) 
	{
		return jobRepository.findByJobTitleAndLocation(jobTitle, location);
	}

	@Override
	public List<Job> findJobsByEmploymentType(EmploymentType type) 
	{
		return jobRepository.findByEmploymentType(type);
	}
}
