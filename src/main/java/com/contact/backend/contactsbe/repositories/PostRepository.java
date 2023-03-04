package com.contact.backend.contactsbe.repositories;

import com.contact.backend.contactsbe.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
