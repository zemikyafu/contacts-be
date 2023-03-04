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
import org.springframework.security.authentication.BadCredentialsException;
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
		String jwt;
		JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();
            try {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				 jwt = jwtTokenProvider.generateToken(authentication);
				 jwtAuthenticationResponse.setStatus(true);
				 jwtAuthenticationResponse.setMessage("SignIn Successful");
				 jwtAuthenticationResponse.setTokenType("Bearer");
				jwtAuthenticationResponse.setAccessToken(jwt);
			  }
			catch(BadCredentialsException ex)
			{
				jwtAuthenticationResponse.setStatus(false);
				jwtAuthenticationResponse.setMessage("SignIn Fail");

			}

			return ResponseEntity.ok(jwtAuthenticationResponse);

	}

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return  ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already taken"));

		}
		else
		{

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

}


