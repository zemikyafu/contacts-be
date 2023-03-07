package com.contact.backend.contactsbe.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private Long id ;
    private String name;
	private boolean status ;

	public JwtAuthenticationResponse() {
	}
	//	public JwtAuthenticationResponse(String accessToken) {
//		this.accessToken = accessToken;
//	}

}
