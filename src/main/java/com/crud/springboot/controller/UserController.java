package com.crud.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.springboot.entity.AuthRequest;
import com.crud.springboot.entity.User;
import com.crud.springboot.entity.UserInfo;
import com.crud.springboot.service.JwtService;
import com.crud.springboot.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/new")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return userService.newUser(userInfo);
	}
	
	@PostMapping("/addUser")
	 public ResponseEntity<User> addUser(@RequestBody User user)  {
		
		User createdUser = userService.createUser(user);
	    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/addUsers")
	public List<User> addUsers(@RequestBody List<User> users) {
		return userService.createUsers(users);
	}
	
	@GetMapping("/User/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
	User user = userService.getUserById(id);
	if(user!=null) {
	ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
	 return response;
	 } else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 	}
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("Users/{pageNo}/{recordCount}")
	public List<User> getAllUsers(@PathVariable int pageNo,@PathVariable int recordCount) {
		return userService.getUsers( pageNo,recordCount);
	}

	@PutMapping("/updateUser")
	 public ResponseEntity<User> updateUser(@RequestBody User user){
		User updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);
	}
	@DeleteMapping("/delUser/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable int id){
		
		String deletedUser = userService.deleteUserById(id);                   
		ResponseEntity<String> response = new ResponseEntity<String>(deletedUser, HttpStatus.OK);
		return response;
	}
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(),authRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
	} else {
		throw new UsernameNotFoundException("invalid user request");
	}
	}
}