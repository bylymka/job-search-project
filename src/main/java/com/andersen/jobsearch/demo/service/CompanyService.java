package com.andersen.jobsearch.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.dto.CompanyDto;
import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.exception.EntityAlreadyExistAuthenticationException;

public interface CompanyService
{
	Company findById(Long id);
	
	Company modifyCompany(CompanyDto companyDto, Long codeEDRPOU);
	
	//boolean checkCompanyByCodeEDRPOU(Long codeEDRPOU);
}
