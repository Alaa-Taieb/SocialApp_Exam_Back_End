package com.codingdodjo.SocialAppExam.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdodjo.SocialAppExam.controller.AuthenticationRequest;
import com.codingdodjo.SocialAppExam.controller.AuthenticationResponse;
import com.codingdodjo.SocialAppExam.controller.RegisterRequest;
import com.codingdodjo.SocialAppExam.model.Role;
import com.codingdodjo.SocialAppExam.model.User;
import com.codingdodjo.SocialAppExam.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(null, request.getName(), passwordEncoder.encode(request.getPassword()), request.getEmail(), request.getAlias(),/* null, */ /* null, */ Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
}
