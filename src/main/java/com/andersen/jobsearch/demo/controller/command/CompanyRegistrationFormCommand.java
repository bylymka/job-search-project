package com.andersen.jobsearch.demo.controller.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import com.andersen.jobsearch.demo.entity.Company;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegistrationFormCommand
{
	@NotBlank
	@Size(min = 3, max = 255)
	private String companyName;
	
	@NotBlank
	@Positive
	private Long companyCodeEDRPOU;
	
	@NotBlank
	@Size(max = 255)
	private String companyAddress;
	
	@NotBlank
	@Size(max = 2000)
	private String companyDescription;
	
	@NotBlank
	@Size(max = 255)
	private String companyIndustry;
	
	@NotBlank
	@Positive
	private Integer companyEmployeesNum;
	
	public static Company getCompanyFromForm(CompanyRegistrationFormCommand registrationForm)
	{
		Company company = Company.builder()
				.name(registrationForm.getCompanyName())
				.codeEDRPOU(registrationForm.getCompanyCodeEDRPOU())
				.description(registrationForm.getCompanyDescription())
				.employeesNum(registrationForm.getCompanyEmployeesNum())
				.industry(registrationForm.getCompanyIndustry())
				.address(registrationForm.getCompanyAddress())
				.build();
		
		return company;
	}
}
