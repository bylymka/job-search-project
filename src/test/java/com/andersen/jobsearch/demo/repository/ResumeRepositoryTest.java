package com.andersen.jobsearch.demo.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;
import com.andersen.jobsearch.demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ResumeRepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ResumeRepository resumeRepository;
	
	private Employee employee;
	private Resume resume;
	
	@Before
	public void initialize()
	{
		User user = User.builder()
			.username("iklymenko")
			.firstName("Inna")
			.lastName("Klymenko")
			.password("qwerty")
			.email("innaklymenko@gmail.com")
			.phoneNum("+380981234567")
			.build();
		employee = Employee.builder()
			.user(user)
			.build();
		entityManager.persist(employee);
		entityManager.flush();
		
		resume = Resume.builder()
			.employee(employee)
			.desiredPosition("Java Developer")
			.city("Lviv")
			.workExperience("Andersen, Java dev, 2 years")
			.skills("Java Core, Spring")
			.build();
		resumeRepository.save(resume);
		
		entityManager.persist(resume);
		entityManager.flush();
	}
	
	@Test
	public void findByEmployeeTest()
	{
		List<Resume> resumes = resumeRepository.findByEmployee(employee);
		assertTrue(resumes.contains(resume));
	}
	
	@Test
	public void findByDesiredPositionContainingIgnoreCaseTest()
	{
		List<Resume> resumes = resumeRepository.findByDesiredPositionContainingIgnoreCase("java developer");
		assertTrue(resumes.contains(resume));
	}
	
	@Test
	public void findByCityIgnoreCaseTest()
	{
		List<Resume> resumes = resumeRepository.findByCityIgnoreCase("lviv");
		assertTrue(resumes.contains(resume));
	}
	
	@Test
	public void findByDesiredPositionContainingIgnoreCaseAndCityIgnoreCaseTest()
	{
		List<Resume> resumes = resumeRepository.findByDesiredPositionContainingIgnoreCaseAndCityIgnoreCase("developer", "lviv");
		assertTrue(resumes.contains(resume));
	}
}
