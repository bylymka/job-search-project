package com.andersen.jobsearch.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.andersen.jobsearch.demo.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepositoryTest
{
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;

	private User user;
	
	@Before
	public void initializeUser()
	{
		user = User.builder()
			.username("iklymenko")
			.firstName("Inna")
			.lastName("Klymenko")
			.password("qwerty")
			.email("innaklymenko@gmail.com")
			.phoneNum("+380981234567")
			.build();
	}
	
	@Test
	public void saveUser()
	{
		User userFromDb = userRepository.save(user);
		assertThat(userFromDb).isEqualTo(user);
	}
	
	@Test
	public void findUserByUsernameAndPasswordTest()
	{
		userRepository.save(user);
		
		String username = "iklymenko";
		String password = "qwerty";
		
		Optional<User> found = userRepository.findUserByUsernameAndPassword(username, password);
		
		if(found.isPresent())
			assertThat(found.get()).isEqualTo(user);
		else
			fail("User was not found");
	}
	
	@Test
	public void findByUsernameTest()
	{
		userRepository.save(user);
		String username = "iklymenko";
		
		Optional<User> found = userRepository.findByUsername(username);
		
		if(found.isPresent())
			assertThat(found.get()).isEqualTo(user);
		else
			fail("User was not found");
	}
	
	@Test
	public void existsUserByUsernameTest()
	{
		userRepository.save(user);
		String username = "iklymenko";
	
		assertTrue("User was not found", userRepository.existsUserByUsername(username));
	}
}
