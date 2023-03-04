package com.contact.backend.contactsbe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
  //  @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @Column(columnDefinition = "Text",name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post() {
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user=user;
    }

}
