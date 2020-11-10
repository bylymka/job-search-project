package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.EmploymentType;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.repository.JobRepository;
import com.andersen.jobsearch.demo.service.JobService;

@Service
public class JobServiceImpl implements JobService
{
	
	/*@Autowired
	JobRepository jobRepository;
	
	public JobServiceImpl(JobRepository jobRepository)
	{
		this.jobRepository = jobRepository;
	}

	@Override
	public Job saveJob(Job job)
	{
		return jobRepository.save(job);
	}

	@Override
	public Optional<Job> modifyJob(Job job) 
	{
		Optional<Job> jobFromDb = jobRepository.findById(job.getId());
		
		jobFromDb.get().setId(job.getId());
		jobFromDb.get().setJobTitle(job.getJobTitle());
		jobFromDb.get().setCompany(job.getCompany());
		jobFromDb.get().setDescription(job.getDescription());
		jobFromDb.get().setEmployer(job.getEmployer());
		jobFromDb.get().setEmploymentType(job.getEmploymentType());
		jobFromDb.get().setIndustry(job.getIndustry());
		jobFromDb.get().setLocation(job.getLocation());
		jobFromDb.get().setPostedOn(job.getPostedOn());
		jobFromDb.get().setSalary(job.getSalary());
		jobFromDb.get().setSkills(job.getSkills());
		jobFromDb.get().setStatus(job.getStatus());
		
		jobRepository.save(jobFromDb.get());
		return jobFromDb;
	}

	@Override
	public void deleteJob(Long jobId)
	{
		jobRepository.deleteById(jobId);
	}

	@Override
	public Optional<Job> getJobById(Long jobId)
	{
		return jobRepository.findById(jobId);
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

	//@Override
	//public List<Job> findJobsByTitleAndLocationAndSalaryGreaterThan(String jobTitle, String location, int minSalary)
	//{
	//	return jobRepository.findByJobTitleAndLocationAndSalaryGreaterThan(jobTitle, location, minSalary);
	//}

	@Override
	public List<Job> findJobsByJobTitleAndLocation(String jobTitle, String location)
	{
		return jobRepository.findByJobTitleAndLocation(jobTitle, location);
	}

	@Override
	public List<Job> findJobsByEmployementType(EmploymentType type) 
	{
		return jobRepository.findByEmploymentType(type);
	}*/
}
