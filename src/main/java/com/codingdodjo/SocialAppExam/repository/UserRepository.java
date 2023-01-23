package com.codingdodjo.SocialAppExam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingdodjo.SocialAppExam.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);
    public Optional<User> findById(Long id);
}
