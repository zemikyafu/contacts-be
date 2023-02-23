package com.contact.backend.contactsbe.repositories;
import com.contact.backend.contactsbe.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
