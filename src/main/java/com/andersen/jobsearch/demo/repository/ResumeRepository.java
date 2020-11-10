package com.andersen.jobsearch.demo.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.Resume;

/**
 * Repository Interface for {@link Resume} class.
 */

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>
{
	/*List<Resume> findByEmployee(Employee employee);
	List<Resume> findByDesiredPositionContainingIgnoreCase(String desiredPosition);
	List<Resume> findByCityIgnoreCase(String city);
	List<Resume> findByDesiredPositionContainingIgnoreCaseAndCityIgnoreCase(String desiredPositon, String location);*/
}