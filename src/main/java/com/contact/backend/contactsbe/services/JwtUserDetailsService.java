package com.contact.backend.contactsbe.services;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service

public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws
            UsernameNotFoundException {
        System.out.println("called before  "+email);
        if ("zemikfcb@gmail.com".equals(email)) {
            System.out.println("called userName"+email);
            return new User("zemikfcb@gmail.com",
                    "test",
                    new ArrayList<>());

        } else {
            System.out.println("email not equal");
            throw new UsernameNotFoundException("User not found with username: " + email);

        }
    }
}
