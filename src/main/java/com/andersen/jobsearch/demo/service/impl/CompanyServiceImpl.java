package com.andersen.jobsearch.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;
import com.andersen.jobsearch.demo.repository.CompanyRepository;
import com.andersen.jobsearch.demo.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService
{

	/*@Autowired
	CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository)
	{
		this.companyRepository = companyRepository;
	}

	@Override
	public Company findById(Long id) 
	{
		return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("The company with id " + id + " does not exist."));
	}

	@Override
	public Company saveCompany(Company company) throws EntityAlreadyExistAuthenticationException 
	{
		if(companyRepository.existsCompanyByNameAndCodeEDRPOU(company.getName(), company.getCodeEDRPOU()))
				throw new EntityAlreadyExistAuthenticationException(
						"Company with name " + company.getName() + " and code EDRPOU " + company.getCodeEDRPOU() + " already exists.");
		return companyRepository.save(company);
	}

	@Override
	public Company modifyCompany(Company company)
	{	
		Company companyFromDb = companyRepository.findById(company.getId()).
				orElseThrow(() -> new IllegalArgumentException("The company with id " + company.getId() + " does not exist."));
		companyFromDb.setId(company.getId());
		companyFromDb.setName(company.getName());
		companyFromDb.setIndustry(company.getIndustry());
		companyFromDb.setDescription(company.getDescription());
		companyFromDb.setCodeEDRPOU(company.getCodeEDRPOU());
		companyFromDb.setEmployeesNum(company.getEmployeesNum());
		companyFromDb.setAddress(company.getAddress());
		companyRepository.save(companyFromDb);
		
		return companyFromDb;
	}*/
}
