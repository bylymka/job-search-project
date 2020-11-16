package com.andersen.jobsearch.demo.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.andersen.jobsearch.demo.entity.Resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeWithInfoAboutEmployeeDto
{
	@NotBlank
	@Size(max=255)
	private String employeeFullName;

	@NotBlank
	@Email
	private String employeeEmail;
	
	@NotBlank
	@Pattern(regexp = "[+]380[\\d]{9}")
	private String employeePhoneNum;
	
	@NotBlank
	@Size(max=255)
	private String desiredPosition;
	
	@NotBlank
	@Size(max=255)
	private String city;
	
	@NotBlank
	@Size(max=2000)
	private String workExperience;
	
	@NotBlank
	@Size(max=2000)
	private String skills;
	
	public static ResumeWithInfoAboutEmployeeDto toDto(Resume resume)
	{
		String employeeFullName = resume.getEmployee().getUser().getFirstName() + " "
				+ resume.getEmployee().getUser().getLastName();
		
		ResumeWithInfoAboutEmployeeDto dto = ResumeWithInfoAboutEmployeeDto.builder()
				.employeeFullName(employeeFullName)
				.desiredPosition(resume.getDesiredPosition())
				.city(resume.getCity())
				.employeeEmail(resume.getEmployee().getUser().getEmail())
				.employeePhoneNum(resume.getEmployee().getUser().getPhoneNum())
				.skills(resume.getSkills())
				.workExperience(resume.getWorkExperience())
				.build();
		
		return dto;
	}
}
