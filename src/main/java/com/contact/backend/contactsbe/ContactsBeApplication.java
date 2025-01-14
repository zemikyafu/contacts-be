package com.contact.backend.contactsbe;

import com.contact.backend.contactsbe.security.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactsBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactsBeApplication.class, args);


    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
