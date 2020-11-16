package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.ResumeDto;
import com.andersen.jobsearch.demo.dto.ResumeWithInfoAboutEmployeeDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;

public interface ResumeService
{
	Resume saveResume(ResumeDto resumeDto, String employeeUsername);
	
	Resume modifyResume(ResumeDto resumeDto, Long resumeId);
	
	void deleteResume(Long resumeId);
	
	Resume getResumeById(Long resumeId);
	
	List<ResumeWithInfoAboutEmployeeDto> findResumesByEmployee(String employeeUsername);
	
	List<ResumeWithInfoAboutEmployeeDto> findResumesByProffesion(String proffesion);
	
	//List<Resume> findResumesByKeyWords(String keyWords);
	
	List<ResumeWithInfoAboutEmployeeDto> findResumesByProffesionAndCity(String proffesion, String city);
	
	List<ResumeWithInfoAboutEmployeeDto> findResumesByCity(String city);
}
