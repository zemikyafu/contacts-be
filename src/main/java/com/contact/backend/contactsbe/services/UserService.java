package com.contact.backend.contactsbe.services;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User save(User post) {
        return userRepository.save(post);
    }
    //
    public User get(Integer id) {
        User user = new User ();
        if ( userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else return user ;
    }
    //
    public Optional<User> updateUser(User user, int id)
    {


        return Optional.of(userRepository.findById(id)
                .map(userFound -> {
                    userFound.setId(id);
                    userFound.setName(user.getName());
                    userFound.setPassword(user.getPassword());
                    userFound.setEmail(user.getEmail());
                    return userRepository.save(user);
                }).orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                }));

    }
    public ResponseMessageDto delete( int id) {
        ResponseMessageDto responseMessageDto= new ResponseMessageDto();
        try{
            userRepository.deleteById(id);

            responseMessageDto.setMessage("Record deleted");
            responseMessageDto.setStatus(true);
        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("Record not  deleted");
            responseMessageDto.setStatus(false);

        }
        return responseMessageDto;

    }


}
