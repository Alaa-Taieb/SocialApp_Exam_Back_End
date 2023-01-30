package com.codingdodjo.SocialAppExam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        User user = userRepository.findById(id)
            .orElseThrow();
        return user;
    }

    public User findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        return user;
    }


}
