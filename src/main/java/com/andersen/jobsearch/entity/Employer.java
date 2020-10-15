package com.andersen.jobsearch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Employer.
 */

@Entity
@Table(name="employers")
@Getter
@Setter
@ToString
public class Employer extends BaseEntity
{	
	@Column(name="UserId")
	private Long userId;
	
	@Column(name="CompanyId")
	private Long companyId; 
}
