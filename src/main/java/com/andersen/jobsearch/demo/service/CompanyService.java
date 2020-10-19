package com.andersen.jobsearch.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Company;

public interface CompanyService
{
	Optional<Company> findById(Long id);
	
	Company saveCompany(Company company);
	
	Company modifyCompany(Company company);
	
	//boolean checkCompanyByCodeEDRPOU(Long codeEDRPOU);
}
