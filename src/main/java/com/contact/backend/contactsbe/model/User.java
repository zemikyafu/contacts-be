package com.contact.backend.contactsbe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Contact> Contact;

    @OneToMany(mappedBy = "user")
    private List<Post> post;

    public Long getId() {
        return id;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;




    @Column(name = "name")
    private String name ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User( String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
