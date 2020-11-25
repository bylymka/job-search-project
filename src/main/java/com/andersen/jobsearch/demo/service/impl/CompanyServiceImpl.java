package com.andersen.jobsearch.demo.service.impl;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.CompanyDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService
{
	private CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository)
	{
		this.companyRepository = companyRepository;
	}

	@Override
	public Company findById(Long id) 
	{
		return companyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("The company with id " + id + " does not exist."));
	}

	@Override
	public Company modifyCompany(CompanyDto companyDto, Long companyId)
	{	
		Company companyFromDb = findById(companyId);
		
		companyFromDb.setName(companyDto.getName());
		companyFromDb.setIndustry(companyDto.getIndustry());
		companyFromDb.setDescription(companyDto.getDescription());
		companyFromDb.setCodeEDRPOU(companyDto.getCodeEDRPOU());
		companyFromDb.setEmployeesNum(companyDto.getEmployeesNum());
		companyFromDb.setAddress(companyDto.getAddress());
		companyRepository.save(companyFromDb);
		
		return companyFromDb;
	}
}
