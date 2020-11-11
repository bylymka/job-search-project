package com.andersen.jobsearch.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;


/**
 * Repository Interface for {@link User} class.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	/**
	 * Finds user by username and password
	 * @param username the username
	 * @param password the password
	 * @return user with such username and password
	 */
	Optional<User> findUserByUsernameAndPassword(String username, String password);

	/**
	 * Finds user by username
	 * @param username
	 * @return user with such username
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Checks users existing
	 * @param username
	 * @return
	 */
	boolean existsUserByUsername(String username);
}