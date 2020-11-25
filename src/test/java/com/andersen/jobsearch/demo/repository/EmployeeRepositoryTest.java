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
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class EmployeeRepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee;
	private User user;
	
	@Before
	public void saveEmployeeToDb()
	{
		user = User.builder()
			.username("kkoop")
			.firstName("Kateryna")
			.lastName("Koop")
			.password("qwerty")
			.email("katekoop@@gmail.com")
			.phoneNum("+380123456789")
			.build();
		
		employee = Employee.builder()
			.user(user)
			.build();
		
		entityManager.persist(employee);
		entityManager.flush();
	}
	
	@Test
	public void findByUserTest()
	{	
		Employee found  = employeeRepository.findByUser(user);
		assertThat(found.getUser()).isEqualTo(user);
	}
}
