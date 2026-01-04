package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.library.entity.User;
import com.library.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@Autowired
	private  AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user, @RequestParam Integer months) {
		User savedUser = userService.register(user, months);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		return ResponseEntity.ok("Login successful for: " + authentication.getName());
	}
}
