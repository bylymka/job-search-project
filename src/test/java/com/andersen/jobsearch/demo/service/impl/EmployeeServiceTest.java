package com.andersen.jobsearch.demo.service.impl;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.andersen.jobsearch.demo.dto.EmployeeDto;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.EmployeeRepository;
import com.andersen.jobsearch.demo.repository.RoleRepository;
import com.andersen.jobsearch.demo.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest
{
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	private Employee employee;
	private User user;
	
	@Before
	public void initialize()
	{
		user = User.builder()
			.id(1L)	
			.username("kkoop")
			.firstName("Kateryna")
			.lastName("Koop")
			.password("qwerty")
			.email("katekoop@gmail.com")
			.phoneNum("+380123456789")
			.build();
			
		employee = Employee.builder()
			.id(1L)
			.user(user)
			.build();
	}
	
	@Test
	public void registerEmployeeTest()
	{
		EmployeeDto employeeDto = EmployeeDto.toDto(employee);
		
		when(userRepository.existsUserByUsername(employeeDto.getUsername())).thenReturn(false);
		when(roleRepository.getOne(2)).thenReturn(new Role(2, "ROLE_EMPLOYEE"));
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		try
		{
			assertThat(employeeService.registerEmployee(employeeDto)).isEqualTo(employee);
		} 
		catch (EntityAlreadyExistAuthenticationException e)
		{
			fail(e.getMessage());
		}	
		
		verify(employeeRepository, times(1)).save(any(Employee.class));
	}
}
