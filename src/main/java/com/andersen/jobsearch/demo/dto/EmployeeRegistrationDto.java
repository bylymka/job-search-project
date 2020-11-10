package com.andersen.jobsearch.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegistrationDto
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
	
	public static Employee fromDto(EmployeeRegistrationDto dto)
	{
		Employee employee = new Employee();
		
		User user = User.builder()
				.username(dto.getUsername())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.password(dto.getPassword())
				.phoneNum(dto.getPhoneNum())
				.email(dto.getEmail())
				.build();	
		employee.setUser(user);
		return employee;
	}
	
	public static EmployeeRegistrationDto toDto(Employee employee)
	{
		EmployeeRegistrationDto dto = EmployeeRegistrationDto.builder()
				.username(employee.getUser().getUsername())
				.firstName(employee.getUser().getFirstName())
				.lastName(employee.getUser().getLastName())
				.password(employee.getUser().getPassword())
				.phoneNum(employee.getUser().getPhoneNum())
				.email(employee.getUser().getEmail())
				.build();
		return dto;
	}
}
