package com.andersen.jobsearch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Role;


/**
 * Repository Interface for {@link Role} class.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{
	
}
