package com.contact.backend.contactsbe.repositories;
import com.contact.backend.contactsbe.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {

   List<Contact> findByUser_id(Long user_id);
}
