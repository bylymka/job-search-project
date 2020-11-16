package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.JobDto;
import com.andersen.jobsearch.demo.dto.FullJobDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;

public interface JobService
{
	Job saveJob(JobDto job, String employerUsername);
	
	Job modifyJob(JobDto jobDto, Long jobId);
	
	void deleteJob(Long jobId);
	
	List<Job> getAllJobs();
	
	List<Job> findJobsByEmployer(String employerUsername);
	
	List<Job> findJobsByTitle(String jobTitle);
	
	List<Job> findJobsByCity(String city);
	
	List<Job> findJobsByCompany(String companyName);
	
	//List<Job> findJobsByTitleAndLocationAndSalaryGreaterThan(String jobTitle, String location, int salary);
	
	List<Job> findJobsByProffesionAndCity(String proffesion, String city);
	
	List<Job> findJobsByEmploymentType(String type);
}
