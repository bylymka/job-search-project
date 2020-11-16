package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;

public interface JobService
{
	Job saveJob(JobDto job, String employerUsername);
	
	Job modifyJob(JobDto jobDto, Long jobId);
	
	void deleteJob(Long jobId);
	
	List<Job> getAllJobs();
	
	List<JobDto> findJobsByEmployer(String employerUsername);
	
	List<JobDto> findJobsByTitle(String jobTitle);
	
	List<JobDto> findJobsByLocation(String location);
	
	List<JobDto> findJobsByCompany(String companyName);
	
	//List<Job> findJobsByTitleAndLocationAndSalaryGreaterThan(String jobTitle, String location, int salary);
	
	List<JobDto> findJobsByJobTitleAndLocation(String jobTitle, String location);
	
	List<JobDto> findJobsByEmploymentType(String type);
}
