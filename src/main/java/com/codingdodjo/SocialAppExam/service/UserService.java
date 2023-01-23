package com.codingdodjo.SocialAppExam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.repository.UserRepository;

import com.codingdodjo.SocialAppExam.exception.UserNotFoundException;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id "+ id +" was not found");
        }
        return optionalUser.get();
    }
}
