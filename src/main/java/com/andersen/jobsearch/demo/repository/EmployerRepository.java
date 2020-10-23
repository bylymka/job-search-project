package com.andersen.jobsearch.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.Employer;

/**
 * Repository Interface for {@link Employer} class.
 */

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>
{	

}
