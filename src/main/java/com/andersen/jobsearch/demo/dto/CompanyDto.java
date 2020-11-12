package com.andersen.jobsearch.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto
{
	@NotBlank
	@Size(max=255)
	private String name;
	
	@Positive
	private Long codeEDRPOU;
	
	@Size(max=500)
	private String address;
	
	@Size(max=2000)
	private String description;
	
	@Size(max=255)
	private String industry;
	
	@PositiveOrZero
	private Integer employeesNum;
	
	public static CompanyDto toDto(Company company)
	{
		CompanyDto companyDto = CompanyDto.builder()
				.name(company.getName())
				.codeEDRPOU(company.getCodeEDRPOU())
				.address(company.getAddress())
				.description(company.getDescription())
				.industry(company.getIndustry())
				.employeesNum(company.getEmployeesNum())
				.build();
		
		return companyDto;
	}
	
	public static Company fromDto(CompanyDto companyDto)
	{
		Company company = Company.builder()
				.name(companyDto.getName())
				.codeEDRPOU(companyDto.getCodeEDRPOU())
				.address(companyDto.getAddress())
				.description(companyDto.getDescription())
				.industry(companyDto.getIndustry())
				.employeesNum(companyDto.getEmployeesNum())
				.build();
		
		return company;
	}
}
