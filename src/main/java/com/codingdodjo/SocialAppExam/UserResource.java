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

/**
* UserResource is a RestController class that maps the endpoint '/user' and provides methods to handle the requests
* related to the User model.
* It uses the UserService to perform the logic and business operations related to the User model.
*/
@RestController
@RequestMapping("/user")
public class UserResource {
    /**
    * UserService is used to perform the logic and business operations related to the User model.
    */
    private final UserService userService;

    /**
    * Constructor that autowires the UserService
    * @param userService the UserService to be autowired
    */
    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }

    /**
    * Method that maps the endpoint '/user/{id}' and returns the user with the given id
    * @param id the user's id
    * @return the user with the given id if found, otherwise returns HTTP status 404 (not found)
    */
    @GetMapping("/{id}")
    public ResponseEntity<User>getUserById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    } 

    
}
