package com.andersen.jobsearch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Company;

/**
 * Repository Interface for {@link Company} class.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
	
}