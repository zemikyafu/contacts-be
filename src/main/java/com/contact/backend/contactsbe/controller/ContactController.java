package com.contact.backend.contactsbe.controller;

import com.contact.backend.contactsbe.model.Contact;
import com.contact.backend.contactsbe.security.CurrentUser;
import com.contact.backend.contactsbe.security.UserPrincipal;
import com.contact.backend.contactsbe.services.ContactService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.contact.backend.contactsbe.repositories.dto.ResponseMessageDto;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private ContactService contactService ;

    public ContactController(ContactService contactService)
    {
        this.contactService=contactService;
    }
    @GetMapping("/getAllContact")
    public List< Contact> getAllContact() {
        return contactService.listAll();
    }

    @PostMapping("/creatContact")
    public  Contact creatContact(@RequestBody Contact contact,@CurrentUser UserPrincipal currentUser)
    {
        return  contactService.save(contact,currentUser);
    }

    @GetMapping("/findContact/{id}")
    public Contact getContact(@PathVariable int id ) {
        return contactService.get(id);
    }

    @DeleteMapping ("/deleteContact/{id}")
    public ResponseMessageDto deleteContact(@PathVariable int id ) {

        return contactService.delete(id);
    }

    @PutMapping ("/updateContact/{id}")
    public ResponseMessageDto updateContact(@RequestBody Contact contact,@PathVariable int id ) {

        return contactService.updateContact(contact,id);
    }


}
