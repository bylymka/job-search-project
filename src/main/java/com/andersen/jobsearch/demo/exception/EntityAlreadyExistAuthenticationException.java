package com.andersen.jobsearch.demo.exception;

//import org.springframework.security.core.AuthenticationException;

public class EntityAlreadyExistAuthenticationException extends Exception
{
	public EntityAlreadyExistAuthenticationException(String msg)
	{
		super(msg);
	}
}
