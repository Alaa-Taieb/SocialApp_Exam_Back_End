package com.codingdodjo.SocialAppExam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.repository.UserRepository;

import com.codingdodjo.SocialAppExam.exception.UserNotFoundException;

/**
* UserService is a service class that provides methods to interact with the User model.
* It uses the UserRepository to perform CRUD operations on the User model.
*/
@Service
public class UserService {
    
    /**
    * UserRepository is used to perform CRUD operations on the User model.
    */
    private final UserRepository userRepository;

    /**
    * Constructor that autowires the UserRepository
    * @param userRepository the UserRepository to be autowired
    */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    /**
    * Method to find a user by id
    * @param id the user's id
    * @return the user if found, otherwise throws UserNotFoundException
    * @throws UserNotFoundException when the user is not found
    */
    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id "+ id +" was not found");
        }
        return optionalUser.get();
    }

    /**
    * getUserFriends method
    * This method retrieves the friends of a user
    * @param user the user whose friends are to be retrieved
    * @return List of friends of the user
    * @throws UserNotFoundException if the user is not found
    */
    public List<User> getUserFriends(User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email "+ user.getEmail() +" was not found");
        }
        return new ArrayList<User>(optionalUser.get().getFriends());
    }
}
