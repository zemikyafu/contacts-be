package com.contact.backend.contactsbe.controller;

import java.util.*;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.services.UserService;
import org.springframework.beans.factory.annotation.*;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUser() {
        return userService.listAll();
    }

    @PostMapping("/creatUser")
    public  User creatUser(@RequestBody User user)
    {
        return  userService.save(user);
    }

    @GetMapping("/findUser/{id}")
    public User getUser(@PathVariable int id ) {
        return userService.get(id);
    }

    @DeleteMapping ("/deleteUser/{id}")
    public ResponseMessageDto deleteUser(@RequestBody User newUser, @PathVariable int id ) {

        return userService.delete(id);
    }

    @PutMapping ("/updateUser/{id}")
    public Optional<User> updateUser(@RequestBody User user,@PathVariable int id ) {

        return userService.updateUser(user,id);
    }


}
