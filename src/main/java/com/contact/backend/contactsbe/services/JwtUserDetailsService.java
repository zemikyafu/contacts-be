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
//@Override
//public UserDetails loadUserByUsername(Long id) throws
//            UsernameNotFoundException {
//        if (1==id) {
//            return new User("knoldusnext",
//                    "$2a$10$ixlPY3AAd4ty1l6E2IsQ9OFZi2ba9ZQE0bP7RFcGIWNhyFrrT3YUi",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + id);
//
//        }
//    }


}
