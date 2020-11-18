package com.andersen.jobsearch.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.dto.FullJobDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.repository.EmployerRepository;
import com.andersen.jobsearch.demo.repository.JobRepository;
import com.andersen.jobsearch.demo.repository.ResumeRepository;
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
	private ResumeRepository resumeRepository;
	
	@Autowired
	public JobServiceImpl(JobRepository jobRepository, EmployerRepository employerRepository,
			UserRepository userRepository, CompanyRepository companyRepository, ResumeRepository resumeRepository)
	{
		this.jobRepository = jobRepository;
		this.employerRepository = employerRepository;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
		this.resumeRepository = resumeRepository;
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

	@Override
	public List<Job> findJobsByEmployer(String employerUsername) 
	{
		User user = userRepository.findByUsername(employerUsername).
				orElseThrow(() -> new IllegalArgumentException("The employer with username " + employerUsername + " does not exist."));
		
		Employer employer = employerRepository.findByUser(user);
		return jobRepository.findByEmployer(employer);
	}

	@Override
	public List<Job> findJobsByTitle(String jobTitle) 
	{
		return jobRepository.findByJobTitleContainingIgnoreCase(jobTitle);
	}

	@Override
	public List<Job> findJobsByCity(String city) 
	{
		return jobRepository.findByLocationIgnoreCase(city);
	}

	@Override
	public List<Job> findJobsByCompany(String companyName) 
	{
		Company company = companyRepository.findByName(companyName);
		return jobRepository.findByCompany(company);
	}

	@Override
	public List<Job> findJobsByProffesionAndCity(String proffesion, String city) 
	{
		return jobRepository.findByJobTitleContainingIgnoreCaseAndLocationIgnoreCase(proffesion, city);
	}

	@Override
	public List<Job> findJobsByEmploymentType(String type) 
	{
		return jobRepository.findByEmploymentType(type);
	}
	
	@Override
	public void apply(Long resumeId, Long jobId) 
	{
		Resume resume = resumeRepository.findById(resumeId)
				.orElseThrow(() -> new IllegalArgumentException("The resume with id " + resumeId + " does not exist."));
		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new IllegalArgumentException("The job with id " + jobId + " does not exist."));
		
		if(job.getResumes() == null)
			job.setResumes(new ArrayList<Resume>());
		
		job.getResumes().add(resume);
		
		jobRepository.save(job);
	}
	
	@Override
	public List<Resume> getAppliedResumes(Long jobId)
	{
		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new IllegalArgumentException("The job with id " + jobId + " does not exist."));
		
		List<Resume> resumes = job.getResumes();
		return resumes;
	}
}
