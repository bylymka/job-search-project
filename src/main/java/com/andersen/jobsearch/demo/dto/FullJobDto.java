package com.andersen.jobsearch.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullJobDto
{
	private Long jobId;
	
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
	
	@NotBlank
	private String postedOn;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String employerName;
	
	@NotBlank
	private String employerNumber;
	
	@NotBlank
	private String companyName;
	
	@NotBlank
	private String companyIndustry;
	
	@NotBlank
	private int companyEmployeesNum;
	
	public static FullJobDto toDto(Job job)
	{
		FullJobDto jobDto = FullJobDto.builder()
				.jobId(job.getId())
				.jobTitle(job.getJobTitle())
				.description(job.getDescription())
				.industry(job.getIndustry())
				.location(job.getLocation())
				.skills(job.getSkills())
				.salary(job.getSalary())
				.employmentType(job.getEmploymentType())
				.postedOn(job.getPostedOn().toString())
				.status(job.getStatus().toString().replace('_', '-').toLowerCase())
				.employerName(job.getEmployer().getUser().getFirstName())
				.employerNumber(job.getEmployer().getUser().getPhoneNum())
				.companyName(job.getCompany().getName())
				.companyIndustry(job.getCompany().getIndustry())
				.companyEmployeesNum(job.getCompany().getEmployeesNum())
				.build();
		
		return jobDto;
	}
	
	/*public static Job fromDto(JobWithInfoAboutEmployerDto jobDto)
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
	}*/
	
	public static List<FullJobDto> getListOfJobsDto(List<Job> jobs)
	{
		List<FullJobDto> jobsDto = new ArrayList<>();
		jobs.forEach(job -> jobsDto.add(FullJobDto.toDto(job)));
		return jobsDto;
	}
}
