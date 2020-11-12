package com.andersen.jobsearch.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
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
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "[+]380[\\d]{9}")
	private String phoneNum;
	
	public static User fromDto(UserDto dto)
	{
		User user = User.builder()
				.username(dto.getUsername())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.phoneNum(dto.getPhoneNum())
				.email(dto.getEmail())
				.build();	
		
		return user;
	}
	
	public static UserDto toDto(User user)
	{
		UserDto dto = UserDto.builder()
				.username(user.getUsername())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.phoneNum(user.getPhoneNum())
				.email(user.getEmail())
				.build();
		return dto;
	}
}
