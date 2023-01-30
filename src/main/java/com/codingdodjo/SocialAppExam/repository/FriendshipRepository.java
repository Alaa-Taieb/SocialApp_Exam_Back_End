package com.codingdodjo.SocialAppExam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingdodjo.SocialAppExam.model.Friendship;
import com.codingdodjo.SocialAppExam.model.User;

public interface FriendshipRepository extends JpaRepository<Friendship, Long>{
    boolean existsByFirstUserAndSecondUser(User first, User second);

    

    List<Friendship> findByFirstUser(User user);
    List<Friendship> findBySecondUser(User user);
    Friendship findByFirstUserAndSecondUser(User first, User second);
}
