package com.andersen.jobsearch.demo.entity;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Employee.
 */

@Entity
@Table(name="employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity
{	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="DateOfBirth")
	private LocalDate dateOfBirth;
	
	@Column(name="Email")
	@Email
	private String email;
	
	@Column(name="City")
	private String city;
	
	@Column(name="phoneNum")
	private String phoneNum;

	@Basic(fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "employee")
	List<Resume> resumes;
}
