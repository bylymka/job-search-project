package com.andersen.jobsearch.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.andersen.jobsearch.demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class EmployerRepositoryTest
{	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	private Employer employer;
	private User user;
	private Company company;
	
	@Before
	public void saveEmployerToDb()
	{
		user = User.builder()
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
			.build();
		
		entityManager.persist(employer);
		entityManager.flush();
	}
	
	@Test
	public void findByUserTest()
	{	
		Employer found  = employerRepository.findByUser(user);
		assertThat(found.getUser()).isEqualTo(user);
	}
}
