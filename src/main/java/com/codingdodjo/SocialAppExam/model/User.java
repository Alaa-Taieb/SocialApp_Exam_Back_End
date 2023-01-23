package com.codingdodjo.SocialAppExam.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

/**
 * The User class represents a user of the SocialAppExam application.
 * Each user has an id, a name, an email, a password, an imageUrl and a set of friends.
 * The class is annotated with JPA annotations to specify how the class should be mapped to a database table.
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
    
    /**
     * The user's id, it's a primary key and it's generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    
    /**
     * The user's name, it's a required field.
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * The user's email, it's a required field and it must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;
    
    /**
     * The user's password, it's a required field.
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * The user's imageUrl, it's a required field.
     */
    @Column(nullable = false)
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;


    public User(){}

    public User(String name, String email, String password, String imageUrl){
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
