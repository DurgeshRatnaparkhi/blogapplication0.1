package com.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.JwtRequest;
import com.blog.payload.JwtResponse;
import com.blog.payload.UserDto;
import com.blog.security.JwtHelper;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;
    
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        // Authenticate the user
        this.authenticate(request.getUsername(), request.getPassword());

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // Generate JWT token
        String token = jwtHelper.generateToken(userDetails);

        // Prepare the response
        JwtResponse response = JwtResponse.builder()
                .token(token)
                .username(userDetails.getUsername())
                .build();

        // Return the response entity
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //check user name and password are correct
    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info("User authenticated successfully: {}", username);
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", username);
            throw new RuntimeException("Invalid username or password!");
        }
    }
    
    //create user
     
    @PostMapping("/create-user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createuserDto = userService.createUser(userDto);

		return new ResponseEntity<>(createuserDto, HttpStatus.CREATED);
	}
}
