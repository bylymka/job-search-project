package com.andersen.jobsearch.demo.exception;

import org.springframework.security.core.AuthenticationException;

public class EntityAlreadyExistAuthenticationException extends AuthenticationException
{
	public EntityAlreadyExistAuthenticationException(String msg)
	{
		super(msg);
	}
}
