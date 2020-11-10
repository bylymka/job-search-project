package com.andersen.jobsearch.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRegistrationDto
{
	@NotBlank
	@Size(min = 4, max = 45)
	private String username;
	
	@NotBlank
	@Size(min = 4, max = 45)
	private String firstName;
	
	@NotBlank
	@Size(min = 4, max = 45)
	private String lastName;
	
	@NotBlank
    @Size(min = 5, max = 35)
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "[+]380[\\d]{9}")
	private String phoneNum;
	
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
	
	@NotBlank
	@Size(max = 255)
	private String employerPosition;
	
	public static Employer fromDto(EmployerRegistrationDto dto)
	{
		Employer employer = new Employer();
		
		User user = User.builder()
				.username(dto.getUsername())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.password(dto.getPassword())
				.phoneNum(dto.getPhoneNum())
				.email(dto.getEmail())
				.build();
		employer.setUser(user);
		
		Company company = Company.builder()
				.name(dto.companyName)
				.codeEDRPOU(dto.companyCodeEDRPOU)
				.address(dto.companyAddress)
				.description(dto.companyDescription)
				.industry(dto.companyIndustry)
				.employeesNum(dto.companyEmployeesNum)
				.build();
		employer.setCompany(company);
		
		employer.setPosition(dto.employerPosition);
		System.out.println(employer);
		return employer;
	}
	
	public static EmployerRegistrationDto toDto(Employer employer)
	{
		EmployerRegistrationDto dto = EmployerRegistrationDto.builder()
				.username(employer.getUser().getUsername())
				.firstName(employer.getUser().getFirstName())
				.lastName(employer.getUser().getLastName())
				.password(employer.getUser().getPassword())
				.phoneNum(employer.getUser().getPhoneNum())
				.email(employer.getUser().getEmail())
				.companyName(employer.getCompany().getName())
				.companyCodeEDRPOU(employer.getCompany().getCodeEDRPOU())
				.companyAddress(employer.getCompany().getAddress())
				.companyDescription(employer.getCompany().getDescription())
				.companyIndustry(employer.getCompany().getIndustry())
				.companyEmployeesNum(employer.getCompany().getEmployeesNum())
				.employerPosition(employer.getPosition())
				.build();
		return dto;
	}
}
