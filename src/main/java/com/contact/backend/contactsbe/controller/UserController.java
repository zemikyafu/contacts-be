package com.contact.backend.contactsbe.controller;

import java.util.*;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.services.UserService;
import org.springframework.beans.factory.annotation.*;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService=userService;
    }


    @GetMapping("/findUser/{email}")
    public User findUserByEmail(@PathVariable String email ) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser() {
        return userService.listAll();
    }

    @DeleteMapping ("/deleteUser/{id}")
    public ResponseMessageDto deleteUser(@PathVariable int id ) {

        return userService.delete(id);
    }

    @PutMapping ("/updateUser/{id}")
    public ResponseMessageDto updateUser(@RequestBody User user,@PathVariable int id ) {

        return userService.updateUser(user,id);
    }


}
