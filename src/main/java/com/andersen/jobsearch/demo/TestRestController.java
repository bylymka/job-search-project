package com.andersen.jobsearch.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.service.UserService;

@RestController
@RequestMapping("/test-controller")
public class TestRestController
{
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long userId)
	{
		if(userId == null)
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		
		User user = userService.findById(userId).get();
		
		if(user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
