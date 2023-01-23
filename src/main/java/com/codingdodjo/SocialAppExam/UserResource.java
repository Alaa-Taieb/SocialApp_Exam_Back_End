package com.codingdodjo.SocialAppExam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getUserById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    } 
}
