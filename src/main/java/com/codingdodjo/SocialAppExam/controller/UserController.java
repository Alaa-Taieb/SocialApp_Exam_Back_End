package com.codingdodjo.SocialAppExam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdodjo.SocialAppExam.model.Friendship;
import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.service.FriendshipService;
import com.codingdodjo.SocialAppExam.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final FriendshipService friendshipService;
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());    
    }

    @GetMapping(path = "/byemail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        System.out.println("EMAIL :: " + email);
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping(path = "/{id}/friends")
    public ResponseEntity<List<User>> findUserFriends(@PathVariable Long id){
        return ResponseEntity.ok().body(friendshipService.getFriendshipsByUser(id));
    }

    @PostMapping(path = "/{id}/friends/add/{id2}")
    public ResponseEntity<List<User>> addFriend(@PathVariable Long id, @PathVariable Long id2){
        return ResponseEntity.ok().body(friendshipService.addFriendship(id, id2));
    }

    @PostMapping(path = "/{id}/friends/remove/{id2}")
    public ResponseEntity<List<User>> removeFriend(@PathVariable Long id, @PathVariable Long id2){
        List<User> result = friendshipService.removeFriend(id, id2);
        return ResponseEntity.ok().body(result);
    }

}
