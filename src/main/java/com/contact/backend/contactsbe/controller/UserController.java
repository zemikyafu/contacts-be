package com.contact.backend.contactsbe.controller;

import java.util.*;

import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.services.UserService;
import org.springframework.beans.factory.annotation.*;

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

    @DeleteMapping ("/findUsers/{id}")
    public User getUser(@RequestBody User newUser, @PathVariable int id ) {

        return userService.get(id);
    }

    @PutMapping ("/update")
    public Optional<User> deleteUser(@RequestBody User user,@PathVariable int id ) {

        return userService.updateUser(user,id);
    }


}
