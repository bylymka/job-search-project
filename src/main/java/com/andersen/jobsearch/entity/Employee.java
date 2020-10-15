package com.andersen.jobsearch.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
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
public class Employee extends BaseEntity
{
	@Column(name="UserId")
	private Long userId;
	
	@Column(name="Resume")
	private File resume;
}
