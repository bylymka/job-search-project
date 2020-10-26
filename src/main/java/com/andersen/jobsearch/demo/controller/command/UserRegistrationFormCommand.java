package com.andersen.jobsearch.demo.controller.command;

import javax.validation.constraints.*;

import com.andersen.jobsearch.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationFormCommand
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
	
	public static User getUserFromForm(UserRegistrationFormCommand registrationForm)
	{
		User user = User.builder()
				.username(registrationForm.getUsername())
				.firstName(registrationForm.getFirstName())
				.lastName(registrationForm.getLastName())
				.password(registrationForm.getPassword())
				.phoneNum(registrationForm.getPhoneNum())
				.email(registrationForm.getEmail())
				.build();	
		return user;
	}
}
