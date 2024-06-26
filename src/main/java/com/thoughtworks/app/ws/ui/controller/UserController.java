package com.thoughtworks.app.ws.ui.controller;

import com.thoughtworks.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.thoughtworks.app.ws.ui.model.request.UserDetailsRequestModel;
import com.thoughtworks.app.ws.ui.model.response.UserRest;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Map<String, UserRest> users;

	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit",defaultValue = "50") int limit,
			@RequestParam(value="sort",defaultValue = "desc",required = false) String sort) {
		return getSortOrderByPageLimit(page, limit, sort);
	}

	private String getSortOrderByPageLimit(int page, int limit, String sort) {
		return "get user was called with page: " + page +
				", limit: " + limit + " and sort: " + sort;
	}

	@GetMapping(path = "/{userId}", 
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		if ( users.containsKey(userId) ) {
			return new ResponseEntity<>(users.get(userId),HttpStatus.OK );
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT );
		}
	}
	
	@PostMapping( 
			consumes = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					}, 
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					} )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK );
	}
	
	@PutMapping(path = "/{userId}",
			consumes = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					}, 
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					})
	public UserRest updateUser(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails ) {
		
		UserRest storedUserDetails = userService.updateUser(userId, userDetails);
		return storedUserDetails;
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

}
