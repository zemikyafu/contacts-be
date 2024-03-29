package com.contact.backend.contactsbe.controller;

import com.contact.backend.contactsbe.model.Contact;
import com.contact.backend.contactsbe.model.Post;
import com.contact.backend.contactsbe.security.CurrentUser;
import com.contact.backend.contactsbe.security.UserPrincipal;
import com.contact.backend.contactsbe.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService ;
    public PostController( PostService postService)
    {
        this.postService=postService;
    }

    @GetMapping("/getAllPost")
    public List<Post> getAllPost() {
        return postService.listAll();
    }

    @PostMapping("/createPost")
    public  Post createPost(@RequestBody Post post ,@CurrentUser UserPrincipal currentUser)
    {

        return  postService.save(post,currentUser);
    }

    @GetMapping("/findPost/{id}")
    public Post getPost(@PathVariable(value="id") int id ) {
        return postService.get(id);
    }

    @GetMapping("/findPostByUserId/{userid}")
    public ResponseEntity<List<Post>> getContactByUserId(@PathVariable Long userid ) {
        return ResponseEntity.ok((postService.getPostsByUserID(userid)));
    }

    @DeleteMapping ("/deletePost/{id}")
    public ResponseMessageDto deletePost(@PathVariable int id ) {

        return postService.deletePost(id);
    }

    @PutMapping ("/updatePost/{id}")
    public ResponseMessageDto updatePost(@RequestBody Post post,@PathVariable int id ) {

        return postService.updatePost(post,id);
    }
}
