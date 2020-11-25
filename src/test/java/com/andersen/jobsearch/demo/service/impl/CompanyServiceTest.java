package com.andersen.jobsearch.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.andersen.jobsearch.demo.dto.CompanyDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.repository.CompanyRepository;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest
{
	@Mock
	private CompanyRepository companyRepository;
	
	@InjectMocks
	private CompanyServiceImpl companyService;
	
	private Company company;
	
	@Before
	public void initialize()
	{
		company = Company.builder()
			.id(1L)
			.address("Prospekt Pobedy, 22A")
			.name("MyCompany")
			.codeEDRPOU(12345678L)
			.description("Description")
			.industry("IT")
			.employeesNum(200)
			.build();
	}
	
	@Test
	public void modifyCompanyTest()
	{
		Company companyBeforeModifying = company;
		CompanyDto companyDto = CompanyDto.builder()
			.name("My updated company")
			.address("Kyiv, Prospekt Pobedy, 22A")
			.codeEDRPOU(12345671L)
			.description("Updated company")
			.industry("IT and Technologies")
			.employeesNum(250)
			.build();
		
		when(companyRepository.findById(company.getId())).thenReturn(Optional.of(company));
			
		Company companyAfterModifying = companyService.modifyCompany(companyDto, companyBeforeModifying.getId());
		assertThat(companyAfterModifying).isEqualTo(companyBeforeModifying);
	}
}
