package com.andersen.jobsearch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.User;

/**
 * Repository Interface for {@link Employer} class.
 */

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long>
{	
	Employer findByUser(User user);
}
