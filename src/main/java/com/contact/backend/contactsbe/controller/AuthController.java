package com.contact.backend.contactsbe.controller;


import com.contact.backend.contactsbe.dto.ApiResponse;
import com.contact.backend.contactsbe.dto.JwtAuthenticationResponse;
import com.contact.backend.contactsbe.dto.LoginRequest;
import com.contact.backend.contactsbe.dto.SignUpRequest;
import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.UserRepository;
import com.contact.backend.contactsbe.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final String USER_ROLE_NOT_SET = "User role not set";


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;



	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid  @RequestBody LoginRequest loginRequest) {


			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.generateToken(authentication);

			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
		//	throw new BlogapiException(HttpStatus.BAD_REQUEST, "Email is already taken");

		}


		String name = signUpRequest.getName().toLowerCase();

		String email = signUpRequest.getEmail().toLowerCase();

		String password = passwordEncoder.encode(signUpRequest.getPassword());

		User user = new User(email, password,name);

		User result = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
	}

}


