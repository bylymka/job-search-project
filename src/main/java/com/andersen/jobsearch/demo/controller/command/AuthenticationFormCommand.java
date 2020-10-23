package com.andersen.jobsearch.demo.controller.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationFormCommand
{
	@NotBlank
	@Size(min = 4, max = 45)
	private String username;
	
	@NotBlank
    @Size(min = 5, max = 15)
	private String password;
}
