package com.andersen.jobsearch.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class Employer extends BaseEntity
{	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Company.class)
	private Company company;
	
	@Column(name="Position")
	private String position;
	
	@Column(name="Email")
	@Email
	private String email;
}
