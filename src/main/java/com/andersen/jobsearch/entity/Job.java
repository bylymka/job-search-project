package com.andersen.jobsearch.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
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
public class Job extends BaseEntity 
{
	@Column(name="JobTitle")
	private String jobTitle;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="PostedOn")
	private LocalDate postedOn;
	
	@Column(name="EmployerId")
	private Long employerId;
	
	@Column(name="Field")
	private String field;
	
	@Column(name="Status")
	private JobStatus status;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Skills")
	private String skills;
	
}
