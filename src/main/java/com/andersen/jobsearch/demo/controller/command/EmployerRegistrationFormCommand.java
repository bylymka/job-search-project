package com.andersen.jobsearch.demo.controller.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRegistrationFormCommand
{
	@NotNull
	private UserRegistrationFormCommand userRegistrationFormCommand;
	
	@NotNull
	private CompanyRegistrationFormCommand companyRegistrationFormCommand;
	
	@NotBlank
	@Size(min = 10, max = 45)
	private String employerPosition;
	
	public static Employer getEmployerFromForm(EmployerRegistrationFormCommand registrationForm)
	{
		CompanyRegistrationFormCommand companyRegistrationForm = registrationForm.getCompanyRegistrationFormCommand();
		UserRegistrationFormCommand userRegistrationForm = registrationForm.getUserRegistrationFormCommand();
		
		Company company = companyRegistrationForm.getCompanyFromForm(companyRegistrationForm);
		User user = userRegistrationForm.getUserFromForm(userRegistrationForm);
		
		Employer employer = Employer.builder()
				.company(company)
				.user(user)
				.position(((EmployerRegistrationFormCommand) registrationForm).getEmployerPosition())
				.build();
		
		return employer;
	}
}
