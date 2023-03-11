package com.contact.backend.contactsbe.services;

import com.contact.backend.contactsbe.model.Contact;
import com.contact.backend.contactsbe.model.User;
import com.contact.backend.contactsbe.repositories.ContactRepository;
import com.contact.backend.contactsbe.repositories.UserRepository;
import com.contact.backend.contactsbe.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    private UserRepository userRepository;

    public List<Contact> listAll()
    {
        return contactRepository.findAll();
    }

    public Contact save(Contact contact, UserPrincipal currentUser) {
        User user = userRepository.findUserByEmail(currentUser.getEmail());
        contact.setUser(user);
        return contactRepository.save(contact);
    }
    //
    public List<Contact> getContactsByUserID(Long id) {

        return contactRepository.findByUser_id(id);

    }

    public Contact get(Integer id) {
        Contact contact = new Contact ();
        if ( contactRepository.findById(id).isPresent())
            return contactRepository.findById(id).get();
        else return contact ;
    }
    //
//    public Optional<Contact> updateContact(Contact contact, int id)
//    {
//
//
//        return Optional.of(contactRepository.findById(id)
//                .map(contactFound -> {
//                    contactFound.setId(id);
//                    contactFound.setName(contact.getName());
//                    contactFound.setEmail(contact.getEmail());
//                    contactFound.setType(contact.getType());
//                    contactFound.setPhone(contact.getPhone());
//                    return contactRepository.save(contact);
//                }).orElseGet(() -> {
//                    contact.setId(id);
//                    return contactRepository.save(contact);
//                }));
//
//    }

    public ResponseMessageDto updateContact(Contact contact, int id)
    {

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();

        try{


            if (!contactRepository.findById(id).isEmpty())
            {
                Optional<Contact> contactFound = contactRepository.findById(id);
                Contact contactUpdate=  contactFound.get();
                contactUpdate.setEmail(contact.getEmail());
                contactUpdate.setName(contact.getName());
                contactUpdate.setPhone(contact.getPhone());
                contactUpdate.setType(contact.getType());
                contactRepository.save(contactUpdate);
                responseMessageDto.setMessage("Record Updated");
                responseMessageDto.setStatus(true);
            }
            else
            {

                responseMessageDto.setMessage("Contact not found");
                responseMessageDto.setStatus(true);
            }

        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("Contact is Not Updated");
            responseMessageDto.setStatus(false);

        }
        return responseMessageDto;
    }
    public ResponseMessageDto delete( int id) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        try{
            contactRepository.deleteById(id);

            responseMessageDto.setMessage("Contact deleted");
            responseMessageDto.setStatus(true);
        }
        catch (Exception ex)
        {
            responseMessageDto.setMessage("Contact is not  deleted");
            responseMessageDto.setStatus(false);

        }
        return responseMessageDto;

    }

}
