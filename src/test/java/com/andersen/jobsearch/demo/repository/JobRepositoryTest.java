package com.andersen.jobsearch.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.JobStatus;
import com.andersen.jobsearch.demo.entity.User;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class JobRepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private JobRepository jobRepository;
	
	private Job job;
	private Employer employer;
	private Company company;
	
	@Before
	public void initialize()
	{
		User user = User.builder()
			.username("kkoop")
			.firstName("Kateryna")
			.lastName("Koop")
			.password("qwerty")
			.email("katekoop@@gmail.com")
			.phoneNum("+380123456789")
			.build();
				
		company = Company.builder()
			.name("Privat Bank")
			.codeEDRPOU(12345678L)
			.address("Kyiv, Garmatna, 38")
			.description("The biggest bank in Ukraine")
			.industry("Finance")
			.employeesNum(220)
			.build();
				
		employer = Employer.builder()
			.user(user)
			.company(company)
			.position("HR")
			.build();
				
		entityManager.persist(employer);
		entityManager.flush();
			
		job = Job.builder()
			.jobTitle("Java Developer")
			.description("Description")
			.postedOn(LocalDate.now())
			.employer(employer)
			.company(company)
			.industry("IT")
			.status(JobStatus.ACTIVE)
			.location("Lviv")
			.skills("Java Core, Spring")
			.employmentType("full_time")
			.build();
			
		entityManager.persist(job);
		entityManager.flush();
	}
	
	@Test
	public void findByEmployerTest()
	{	
		List<Job> jobs = jobRepository.findByEmployer(employer);
		assertTrue(jobs.contains(job));
	}
	
	@Test
	public void findByJobTitleContainingIgnoreCaseTest()
	{
		String jobTitle = "java";
		List<Job> jobs = jobRepository.findByJobTitleContainingIgnoreCase(jobTitle);
		assertTrue(jobs.contains(job));
	}
	
	@Test
	public void findByLocationIgnoreCaseTest()
	{
		String location = "lviv";
		List<Job> jobs = jobRepository.findByLocationIgnoreCase(location);
		assertTrue(jobs.contains(job));
	}
	
	@Test
	public void findByCompanyTest()
	{
		List<Job> jobs = jobRepository.findByCompany(company);
		assertTrue(jobs.contains(job));
	}
	
	@Test
	public void findByJobTitleContainingIgnoreCaseAndLocationIgnoreCaseTest()
	{
		String location = "lviv";
		String jobTitle = "java";
		List<Job> jobs = jobRepository.
				findByJobTitleContainingIgnoreCaseAndLocationIgnoreCase(jobTitle, location);
		assertTrue(jobs.contains(job));
	}
}
