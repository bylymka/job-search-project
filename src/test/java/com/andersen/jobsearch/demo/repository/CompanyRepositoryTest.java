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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CompanyRepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	private Company company;
	
	@Before
	public void saveCompanyToDb()
	{
		company = Company.builder()
				.address("Prospekt Pobedi, 22A")
				.name("MyCompany")
				.codeEDRPOU(12345678L)
				.description("Description")
				.industry("IT")
				.employeesNum(200)
				.build();
		entityManager.persist(company);
		entityManager.flush();
	}
	
	@Test
	public void findByCodeEDRPOUTest()
	{	
		Company found  = companyRepository.findByCodeEDRPOU(company.getCodeEDRPOU());
		assertThat(found.getCodeEDRPOU()).isEqualTo(company.getCodeEDRPOU());
	}
	
	@Test
	public void findByNameTest()
	{
		Company found  = companyRepository.findByName(company.getName());
		assertThat(found.getName()).isEqualTo(company.getName());
	}
}
