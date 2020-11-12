package com.andersen.jobsearch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;

public interface ResumeService
{
	Resume saveResume(Resume resume);
	
	Optional<Resume> modifyResume(Resume resume);
	
	void deleteResume(Long resumeId);
	
	Resume getResumeById(Long resumeId);
	
	List<Resume> findResumesByEmployee(Employee employee);
	
	List<Resume> findResumesByProffesion(String proffesion);
	
	//List<Resume> findResumesByKeyWords(String keyWords);
	
	List<Resume> findResumesByProffesionAndLocation(String proffesion, String location);
	
	List<Resume> findResumesByCity(String city);
	
	
}
