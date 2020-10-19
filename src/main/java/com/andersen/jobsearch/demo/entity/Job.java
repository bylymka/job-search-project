package com.andersen.jobsearch.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Job extends BaseEntity 
{
	@Column(name="JobTitle")
	private String jobTitle;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="PostedOn")
	private LocalDate postedOn;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employer employer;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Column(name="Industry")
	private String industry;
	
	@Column(name="Status")
	private JobStatus status;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Skills")
	private String skills;
	
	@Column(name="Salary")
	private Integer salary;
	
	@Column(name="EmploymentType")
	private EmploymentType employmentType;
}
