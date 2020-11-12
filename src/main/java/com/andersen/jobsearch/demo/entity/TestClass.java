package com.andersen.jobsearch.demo.entity;

import com.andersen.jobsearch.demo.dto.JobDto;

public class TestClass
{
	public static void main(String[] args) 
	{
		String empType= "full-time";
		EmploymentType employmentType = EmploymentType.valueOf(empType.replace("-", "_").toUpperCase());
		System.out.println(employmentType);
	}
}
