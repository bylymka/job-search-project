package com.andersen.jobsearch.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity
{
	@Column(name="Name")
	private String name;
	
	@Column(name="CodeEDRPOU")
	private Long codeEDRPOU;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Industry")
	private String industry;
	
	@Column(name="EmployeesNum")
	private Integer employeesNum;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Job> jobs;
}

