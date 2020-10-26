package com.andersen.jobsearch.demo.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.service.impl.UserServiceImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	public UserDetailsServiceImpl(UserServiceImpl userServiceImpl)
	{
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User applicationUser = userServiceImpl.findByUsername(username);
		
		if(applicationUser==null)
		{
			throw new UsernameNotFoundException("User with username: " + username + "not found.");
		}
		
		List<GrantedAuthority> authorities = getUserAuthorities(applicationUser.getRoles());
		
		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(),
				applicationUser.getPassword(), authorities);
	}
	
	private List<GrantedAuthority> getUserAuthorities(List<Role> userRoles)
	{
		Set<GrantedAuthority> roles = new HashSet<>();
		userRoles.forEach(role -> roles.add(new SimpleGrantedAuthority(role.getRole().name())));
		
		return new ArrayList<GrantedAuthority>(roles);
	}
}
