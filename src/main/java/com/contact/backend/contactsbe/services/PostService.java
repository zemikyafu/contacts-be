package com.contact.backend.contactsbe.services;

import com.contact.backend.contactsbe.model.Post;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.PostRepository;

import com.contact.backend.contactsbe.repositories.UserRepository;
import com.contact.backend.contactsbe.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    @Autowired
    public PostService( PostRepository postRepository)
    {
        this.postRepository=postRepository;
    }

    @Autowired
    private UserRepository userRepository;

    public List<Post> listAll()
    {
        return postRepository.findAll();
    }

    public Post save(Post post,UserPrincipal currentUser) {
       User user = userRepository.findUserByEmail(currentUser.getEmail());
       post.setUser(user);
       return postRepository.save(post);
    }
    //
    public Post get(Integer id) {
        Post contact = new Post ();
        if ( postRepository.findById(id).isPresent())
            return postRepository.findById(id).get();
        else return contact ;
    }
    //
    public ResponseMessageDto updatePost(Post post,int id)
    {

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();

        try{
            if (!postRepository.findById(id).isEmpty())
            {

                Optional<Post> postFound = postRepository.findById(id);
                Post postUpdate=  postFound.get();
                postUpdate.setTitle(post.getTitle());
                postUpdate.setBody(post.getBody());

                postRepository.save(postUpdate);
                responseMessageDto.setMessage("Record Updated");
                responseMessageDto.setStatus(true);
            }
            else
            {
                responseMessageDto.setMessage("Post not found");
                responseMessageDto.setStatus(false);
            }

        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("Record is Not Updated");
            responseMessageDto.setStatus(false);

        }
     return responseMessageDto;
    }
    public ResponseMessageDto deletePost( int id) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        try{
            postRepository.deleteById(id);
            responseMessageDto.setMessage("Post deleted");
            responseMessageDto.setStatus(true);
        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("Post is not  deleted");
            responseMessageDto.setStatus(false);

        }
        return responseMessageDto;

    }

    public List<Post> getPostsByUserID(Long userid) {

        return postRepository.findByUser_id(userid);
    }
}
