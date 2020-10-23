package com.andersen.jobsearch.demo.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.*;
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication
	(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException 
	{
		try
		{
			User creds = new ObjectMapper().
					readValue(request.getInputStream(), User.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>()));
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException 
	{
		if(authResult.getPrincipal() != null)
		{
			org.springframework.security.core.userdetails.User user =
					(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
			
			String username = user.getUsername();
			
			if(username != null && !username.isEmpty())
			{
				Claims claims = Jwts.claims().setSubject(username);
				List<String> roles = new ArrayList<>();
				
				user.getAuthorities().stream()
						.forEach(authority -> roles.add(authority.getAuthority()));
				
				claims.put("roles", roles);
				
				String token = Jwts.builder()
						.setClaims(claims)
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
						.compact();
			}
		}
	}
}
