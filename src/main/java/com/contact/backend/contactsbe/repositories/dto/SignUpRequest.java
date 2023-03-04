package com.contact.backend.contactsbe.repositories.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
	private String email;

    @NotBlank
    private String password;
}
