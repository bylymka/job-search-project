package com.andersen.jobsearch.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.entity.UserRoles;


/**
 * Repository Interface for {@link UserRoles} class.
 */

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long>
{
	List<User> findUserByRole(Role role);
}
