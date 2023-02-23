package com.contact.backend.contactsbe.services;

import com.contact.backend.contactsbe.model.Contact;
import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.ContactRepository;
import com.contact.backend.contactsbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contact.backend.contactsbe.dto.ResponseMessageDto;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository=contactRepository;
    }

    public List<Contact> listAll()
    {
        return contactRepository.findAll();
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
    //
    public Contact get(Integer id) {
        Contact contact = new Contact ();
        if ( contactRepository.findById(id).isPresent())
            return contactRepository.findById(id).get();
        else return contact ;
    }
    //
    public Optional<Contact> updateUser(Contact contact, int id)
    {


        return Optional.of(contactRepository.findById(id)
                .map(contactFound -> {
                    contactFound.setId(id);
                    contactFound.setName(contact.getName());
                    contactFound.setEmail(contact.getEmail());
                    contactFound.setType(contact.getType());
                    contactFound.setPhone(contact.getPhone());
                    return contactRepository.save(contact);
                }).orElseGet(() -> {
                    contact.setId(id);
                    return contactRepository.save(contact);
                }));

    }
    public ResponseMessageDto delete( int id) {
        ResponseMessageDto responseMessageDto = null;
        try{
            contactRepository.deleteById(id);

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
