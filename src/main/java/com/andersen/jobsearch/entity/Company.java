package com.andersen.jobsearch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Company.
 */

@Entity
@Table(name="companies")
@Getter
@Setter
@ToString
public class Company extends BaseEntity
{
	@Column(name="Name")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Industry")
	private String industry;
	
	@Column(name="EmployeesNum")
	private Integer employeesNum;
}
