package com.andersen.jobsearch.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andersen.jobsearch.demo.entity.AvailableRoles;
import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long>
{	
	List<User> findByRole(AvailableRoles role);
}
