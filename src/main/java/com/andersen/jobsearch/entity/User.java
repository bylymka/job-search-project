package com.andersen.jobsearch.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents User.
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User extends BaseEntity
{
	@Column(name="Username")
	private String username;
	
	@Column(name="Role")
	private Role role;
	
	@Column(name="Password")
	@Size(min = 4, max = 45)
	private String password;
	
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
	
	@Column(name="PhoneNum")
	private String phoneNum;
}
