package com.contact.backend.contactsbe.repositories;

import com.contact.backend.contactsbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
