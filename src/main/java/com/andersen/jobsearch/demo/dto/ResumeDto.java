package com.andersen.jobsearch.demo.dto;

import javax.validation.constraints.NotBlank;
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
public class ResumeDto
{
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
	
	public static Resume fromDto(ResumeDto resumeDto)
	{
		Resume resume = Resume.builder()
				.desiredPosition(resumeDto.getDesiredPosition())
				.city(resumeDto.getCity())
				.workExperience(resumeDto.getWorkExperience())
				.skills(resumeDto.getSkills())
				.build();
		
		return resume;
	}
	
	public static ResumeDto toDto(Resume resume)
	{
		ResumeDto resumeDto = ResumeDto.builder()
				.desiredPosition(resume.getDesiredPosition())
				.city(resume.getCity())
				.workExperience(resume.getWorkExperience())
				.skills(resume.getSkills())
				.build();
		
		return resumeDto;
	}
}
