package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService
{

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public Optional<Company> findById(Long id) 
	{
		return companyRepository.findById(id);
	}

	@Override
	public Company saveCompany(Company company) 
	{
		return companyRepository.save(company);
	}

	@Override
	public Company modifyCompany(Company company)
	{
		Optional<Company> companyFromDb = companyRepository.findById(company.getId());
		companyFromDb.get().setId(company.getId());
		companyFromDb.get().setName(company.getName());
		companyFromDb.get().setIndustry(company.getIndustry());
		companyFromDb.get().setDescription(company.getDescription());
		companyFromDb.get().setCodeEDRPOU(company.getCodeEDRPOU());
		companyFromDb.get().setEmployeesNum(company.getEmployeesNum());
		companyFromDb.get().setAddress(company.getAddress());
		companyRepository.save(companyFromDb.get());
		
		return companyFromDb.get();
	}
}
