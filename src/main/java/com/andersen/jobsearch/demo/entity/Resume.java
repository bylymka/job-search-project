package com.andersen.jobsearch.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Resume.
 */

@Entity
@Table(name="resumes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resume extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Employee employee;
	
	@Column(name="DesiredPosition")
	private String desiredPosition;
	
	@Column(name="City")
	private String city;
	
	@Column(name="WorkExperience")
	private String workExperience;
	
	@Column(name="Skills")
	private String skills;
}
