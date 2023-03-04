package com.contact.backend.contactsbe.repositories;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

   User findUserByEmail(String email);
  Optional<User> findByEmail(String email);

//    default Optional<User> getUser(UserPrincipal currentUser) {
//        System.out.println(currentUser.getUsername());
//        return   findByEmail(currentUser.getUsername());
//
//    }

   Boolean existsByEmail(String email);
}
