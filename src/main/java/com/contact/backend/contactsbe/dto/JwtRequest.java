package com.contact.backend.contactsbe.dto;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    //  private static final long serialVersionUID = 5926468583005150707L;
    private String email;
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {

        return this.email;

    }

    public void setUsername(String username) {

        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}