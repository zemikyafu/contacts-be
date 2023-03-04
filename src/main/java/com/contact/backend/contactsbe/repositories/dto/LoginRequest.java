package com.contact.backend.contactsbe.repositories.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	@NotBlank
//	private String username;
	private String email;

	@NotBlank
	private String password;
}
