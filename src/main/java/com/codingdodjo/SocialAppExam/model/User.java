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

    /**
    * The user's friends, it's a many to many relationship represented
    * by a set of User objects.
    * The fetch type is set to LAZY, which means that the friends are not loaded from the database until they are needed.
    * The join table is called "friends" and it has two columns: "user_id" and "friend_id".
    */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    /**
    * Default constructor
    */
    public User(){}

    /**
    * Constructor to create a new user
    * @param name the user's name
    * @param email the user's email
    * @param password the user's password
    * @param imageUrl the user's imageUrl
    */
    public User(String name, String email, String password, String imageUrl){
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    /**
    * Getter for the user's id
    * @return the user's id
    */
    public Long getId(){
        return id;
    }

    /**
    * Setter for the user's id
    * @param id the user's id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * Getter for the user's name
    * @return the user's name
    */
    public String getName(){
        return name;
    }

    /**
    * Setter for the user's name
    * @param name the user's name
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    * Getter for the user's email
    * @return the user's email
    */
    public String getEmail(){
        return email;
    }

    /**
    * Setter for the user's email
    * @param email the user's email
    */
    public void setEmail(String email){
        this.email = email;
    }

    /**
    * Getter for the user's friends
    * @return the user's friends
    */
    public Set<User> getFriends() {
        return friends;
    }
    /**
    * Setter for the user's friends
    * @param friends the user's friends
    */
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    /**
    * Getter for the user's imageUrl
    * @return the user's imageUrl
    */
    public String getImageUrl(){
        return imageUrl;
    }

    /**
    * Setter for the user's imageUrl
    * @param imageUrl the user's imageUrl
    */
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    /**
    * toString method to return a string representation of the user.
    * @return a string representation of the user
    */
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
