package com.andersen.jobsearch.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto
{
	@NotBlank
	@Size(max=255)
	private String jobTitle;
	
	@Size(max=2000)
	private String description;
	
	@NotBlank
	@Size(max=255)
	private String industry;
	
	@NotBlank
	@Size(max=255)
	private String location;
	
	@NotBlank
	@Size(max=2000)
	private String skills;
	
	@Positive
	private Integer salary;
	
	@NotBlank
	private String employmentType;
	
	private String postedOn;
	
	private String status;
	
	public static JobDto toDto(Job job)
	{
		JobDto jobDto = JobDto.builder()
				.jobTitle(job.getJobTitle())
				.description(job.getDescription())
				.industry(job.getIndustry())
				.location(job.getLocation())
				.skills(job.getSkills())
				.salary(job.getSalary())
				.employmentType(job.getEmploymentType())
				.postedOn(job.getPostedOn().toString())
				.status(job.getStatus().toString().replace('_', '-').toLowerCase())
				.build();
		
		return jobDto;
	}
	
	public static Job fromDto(JobDto jobDto)
	{
		Job job = Job.builder()
				.jobTitle(jobDto.getJobTitle())
				.description(jobDto.getDescription())
				.postedOn(LocalDate.now())
				.industry(jobDto.getIndustry())
				.status(JobStatus.ACTIVE)
				.location(jobDto.getLocation())
				.skills(jobDto.getSkills())
				.salary(jobDto.getSalary())
				.employmentType(jobDto.getEmploymentType())
				.build();
		return job;
	}
}
