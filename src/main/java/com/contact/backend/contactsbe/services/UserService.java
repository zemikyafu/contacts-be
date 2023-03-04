package com.contact.backend.contactsbe.services;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;

import java.util.List;

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

    public User save(User user) {
        return userRepository.save(user);
    }
    //
    public User findUserByEmail(String email) {
        User user = new User ();
        if ( userRepository.findUserByEmail(email).getEmail()!=null)
            return userRepository.findUserByEmail(email);
        else return user ;
    }
    public ResponseMessageDto updateUser(User user, int id)
    {

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();

        try{
            if (!userRepository.findById(id).isEmpty())
            {
                userRepository.save(user);
                responseMessageDto.setMessage("User Updated");
                responseMessageDto.setStatus(true);
            }
            else
            {
                responseMessageDto.setMessage("User is not found");
                responseMessageDto.setStatus(true);
            }

        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("User is Not Updated");
            responseMessageDto.setStatus(false);

        }
        return responseMessageDto;
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
