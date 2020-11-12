package com.andersen.jobsearch.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.repository.ResumeRepository;
import com.andersen.jobsearch.demo.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService
{
	@Autowired
	private ResumeRepository resumeRepository;
	
	
	public ResumeServiceImpl(ResumeRepository resumeRepository)
	{
		this.resumeRepository = resumeRepository;
	}

	@Override
	public Resume saveResume(Resume resume) 
	{
		return resumeRepository.save(resume);
	}

	@Override
	public Optional<Resume> modifyResume(Resume resume) 
	{
		Optional<Resume> resumeFromDb = resumeRepository.findById(resume.getId());
		resumeFromDb.get().setId(resume.getId());
		resumeFromDb.get().setCity(resume.getCity());
		resumeFromDb.get().setDesiredPosition(resume.getDesiredPosition());
		resumeFromDb.get().setEmployee(resume.getEmployee());
		resumeFromDb.get().setSkills(resume.getSkills());
		resumeFromDb.get().setWorkExperience(resume.getWorkExperience());
		resumeRepository.save(resumeFromDb.get());
		return resumeFromDb;
	}

	@Override
	public void deleteResume(Long resumeId) 
	{
		resumeRepository.deleteById(resumeId);
	}

	@Override
	public Resume getResumeById(Long resumeId) 
	{
		return resumeRepository.getOne(resumeId);
	}

	@Override
	public List<Resume> findResumesByEmployee(Employee employee) 
	{
		return resumeRepository.findByEmployee(employee);
	}

	@Override
	public List<Resume> findResumesByProffesion(String proffesion) 
	{
		return findResumesByProffesion(proffesion);
	}

	@Override
	public List<Resume> findResumesByProffesionAndLocation(String proffesion, String location)
	{
		return resumeRepository.
				findByDesiredPositionContainingIgnoreCaseAndCityIgnoreCase(proffesion, location);
	}

	@Override
	public List<Resume> findResumesByCity(String city) 
	{
		return resumeRepository.findByCityIgnoreCase(city);
	}
}
