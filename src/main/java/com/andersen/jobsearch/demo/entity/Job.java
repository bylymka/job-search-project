package com.andersen.jobsearch.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Job.
 */

@Entity
@Table(name="jobs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Job
{
	public Job()
	{
		resumes = new ArrayList<Resume>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="description")
	private String description;
	
	@Column(name="posted_on")
	private LocalDate postedOn;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name="industry")
	private String industry;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private JobStatus status;
	
	@Column(name="location")
	private String location;
	
	@Column(name="skills")
	private String skills;
	
	@Column(name="salary")
	private Integer salary;

	@Column(name="employment_type")
	private String employmentType;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "job_applicants", joinColumns = @JoinColumn(name = "job_id"),
		inverseJoinColumns = @JoinColumn(name = "resume_id"))
	
	private List<Resume> resumes;
}
