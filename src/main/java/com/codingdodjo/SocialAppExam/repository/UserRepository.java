package com.codingdodjo.SocialAppExam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingdodjo.SocialAppExam.model.User;

/**
* UserRepository is an interface that extends JpaRepository to provide basic CRUD operations on the User model.
* It also provides additional methods to find a user by email and id.
*/
public interface UserRepository extends JpaRepository<User, Long>{
    /**
    * Method to find a user by email
    * @param email the user's email
    * @return an Optional object containing the user if found, otherwise an empty Optional
    */
    public Optional<User> findByEmail(String email);

    /**
    * Method to find a user by id
    * @param id the user's id
    * @return an Optional object containing the user if found, otherwise an empty Optional
    */
    public Optional<User> findById(Long id);
}
