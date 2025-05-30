package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping("/")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
//		UserDto createuserDto = userService.createUser(userDto);
//
//		return new ResponseEntity<>(createuserDto, HttpStatus.CREATED);
//	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getuser(@PathVariable Integer userId) {
		UserDto userById = userService.getUserById(userId);

		return ResponseEntity.ok(userById);

	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> allUser = userService.getAllUser();

		return new ResponseEntity<>(allUser, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updateUser = userService.updateUser(userDto, userId);

		return ResponseEntity.ok(updateUser);

	}

	@DeleteMapping("/{userId}")
	public String deleteUserById(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return "Delete successfully ";
	} 
	
	//get by name
	
	
}
