package com.contact.backend.contactsbe.controller;

import com.contact.backend.contactsbe.model.Contact;
import com.contact.backend.contactsbe.services.ContactService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;
import java.util.List;
import java.util.Optional;

@RestController
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
    public  Contact creatContact(@RequestBody Contact contact)
    {
        return  contactService.save(contact);
    }

    @GetMapping("/findContact/{id}")
    public Contact getContact(@PathVariable int id ) {
        return contactService.get(id);
    }

    @DeleteMapping ("/findContact/{id}")
    public ResponseMessageDto deleteContact(@RequestBody Contact newContact, @PathVariable int id ) {

        return contactService.delete(id);
    }

    @PutMapping ("/updateContact")
    public Optional<Contact> updateContact(@RequestBody Contact contact,@PathVariable int id ) {

        return contactService.updateContact(contact,id);
    }


}
