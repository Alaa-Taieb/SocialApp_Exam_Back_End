package com.codingdodjo.SocialAppExam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdodjo.SocialAppExam.model.Friendship;
import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.repository.FriendshipRepository;
import com.codingdodjo.SocialAppExam.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public List<Friendship> getFrienships(){
        return friendshipRepository.findAll();
    }

    public Boolean friendshipExists(Long id_1, Long id_2){
        User user_1 = userRepository.findById(id_1).orElseThrow();
        User user_2 = userRepository.findById(id_2).orElseThrow();

        return friendshipRepository.existsByFirstUserAndSecondUser(user_1, user_2);
    }

    public List<User> addFriendship(Long id_1, Long id_2){
        User user_1 = userRepository.findById(id_1).orElseThrow();
        User user_2 = userRepository.findById(id_2).orElseThrow();
        Friendship friendship = new Friendship();
        User firstUser = user_1;
        User secondUser = user_2;
        if(user_1.getId() > user_2.getId()){
            firstUser = user_2;
            secondUser = user_1;
        }
        if(!friendshipRepository.existsByFirstUserAndSecondUser(firstUser, secondUser)){
            friendship.setFirstUser(firstUser);
            friendship.setSecondUser(secondUser);
            friendshipRepository.save(friendship);
        }
        return getFriendshipsByUser(id_1);
    }

    public List<User> getFriendshipsByUser(Long id){
        User user = userRepository.findById(id).orElseThrow();

        List<Friendship> friendshipByFirstUser = friendshipRepository.findByFirstUser(user);
        List<Friendship> friendshipBySecondUser = friendshipRepository.findBySecondUser(user);
        List<User> userFriends = new ArrayList<User>();

        for(Friendship friendship : friendshipByFirstUser){
            userFriends.add(userRepository.findById(friendship.getSecondUser().getId()).orElseThrow());
        }
        for(Friendship friendship : friendshipBySecondUser){
            userFriends.add(userRepository.findById(friendship.getFirstUser().getId()).orElseThrow());
        }
        return userFriends;
    }

    /* public List<User> removeFriend(Long user_id, Long friend_id){
        User user = userRepository.findById(user_id).orElseThrow();
        User friend = userRepository.findById(friend_id).orElseThrow();

        if(friendshipExists(user_id, friend_id)){
            List<Friendship> friendships = friendshipRepository.findByFirstUser(user);
            friendships.forEach(item -> {
                if(item.getSecondUser().getId() == friend_id){
                    friendshipRepository.deleteById(item.getId());
                    return;
                }
            });
            return getFriendshipsByUser(user_id);
        }else if(friendshipExists(friend_id, user_id)){
            List<Friendship> friendships = friendshipRepository.findBySecondUser(user);
            friendships.forEach(item -> {
                if(item.getSecondUser().getId() == friend_id){
                    friendshipRepository.deleteById(item.getId());
                    return;
                }
            });
            return getFriendshipsByUser(friend_id);
        }
        return null;
    } */

    public List<User> removeFriend(Long user_id, Long friend_id){
        User user = userRepository.findById(user_id).orElseThrow();
        User friend = userRepository.findById(friend_id).orElseThrow();
        Friendship friendship = friendshipRepository.findByFirstUserAndSecondUser(user, friend);
        if(friendship == null) {
            friendship = friendshipRepository.findByFirstUserAndSecondUser(friend, user);
        }
        if(friendship != null) {
            friendshipRepository.delete(friendship);
        }
        return getFriendshipsByUser(user_id);
    }
}
