package com.andersen.jobsearch.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.ResumeDto;
import com.andersen.jobsearch.demo.dto.FullResumeDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.repository.EmployeeRepository;
import com.andersen.jobsearch.demo.repository.ResumeRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;
import com.andersen.jobsearch.demo.service.ResumeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService
{
	private ResumeRepository resumeRepository;
	private UserRepository userRepository;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public ResumeServiceImpl(ResumeRepository resumeRepository, UserRepository userRepository,
			EmployeeRepository employeeRepository)
	{
		this.resumeRepository = resumeRepository;
		this.userRepository = userRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Resume saveResume(ResumeDto resumeDto, String employeeUsername) 
	{
		User user = userRepository.findByUsername(employeeUsername).
				orElseThrow(() -> new IllegalArgumentException("The employee with username " + employeeUsername + 
						" does not exist."));
		
		Employee employee = employeeRepository.findByUser(user);
		Resume resume = ResumeDto.fromDto(resumeDto);
		resume.setEmployee(employee);
		
		if(employee.getResumes() == null)
			employee.setResumes(new ArrayList<Resume>());
		
		employee.getResumes().add(resume);
		return resumeRepository.save(resume);
	}

	@Override
	public Resume modifyResume(ResumeDto resumeDto, Long resumeId) 
	{
		Resume resume = resumeRepository.findById(resumeId).
				orElseThrow(() -> new IllegalArgumentException("The resume with id " + resumeId + " does not exist."));
		
		resume.setDesiredPosition(resumeDto.getDesiredPosition());
		resume.setCity(resumeDto.getCity());
		resume.setSkills(resumeDto.getSkills());
		resume.setWorkExperience(resumeDto.getWorkExperience());
		
		return resumeRepository.save(resume);
	}

	@Override
	public void deleteResume(Long resumeId) 
	{
		resumeRepository.deleteById(resumeId);
	}

	@Override
	public Resume getResumeById(Long resumeId) 
	{
		Resume resume =  resumeRepository.findById(resumeId).
				orElseThrow(() -> new IllegalArgumentException("The resume with id " + resumeId + " does not exist."));
		
		return resume;
	}
	
	@Override
	public List<Resume> findResumesByEmployee(String employeeUsername) 
	{
		User user = userRepository.findByUsername(employeeUsername).
				orElseThrow(() -> new IllegalArgumentException("The employee with username " + employeeUsername + " does not exist."));
		
		Employee employee = employeeRepository.findByUser(user);
		return resumeRepository.findByEmployee(employee);
	}

	@Override
	public List<Resume> findResumesByProffesion(String proffesion) 
	{
		return resumeRepository.findByDesiredPositionContainingIgnoreCase(proffesion);
	}

	@Override
	public List<Resume> findResumesByProffesionAndCity(String proffesion, String city) 
	{
		return resumeRepository.findByDesiredPositionContainingIgnoreCaseAndCityIgnoreCase(proffesion, city);
	}

	@Override
	public List<Resume> findResumesByCity(String city) 
	{
		return resumeRepository.findByCityIgnoreCase(city);
	}
}
