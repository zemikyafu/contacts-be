package com.contact.backend.contactsbe.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType ;
    private String message;
	private boolean status ;

	public JwtAuthenticationResponse() {
	}
	//	public JwtAuthenticationResponse(String accessToken) {
//		this.accessToken = accessToken;
//	}

}
