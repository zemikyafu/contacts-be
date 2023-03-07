package com.contact.backend.contactsbe.repositories;

import com.contact.backend.contactsbe.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser_id(Long userid);

}
