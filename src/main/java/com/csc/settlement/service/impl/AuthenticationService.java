package com.csc.settlement.service.impl;

import com.csc.settlement.entity.UserEntity;
import com.csc.settlement.exception.UserAlreadyExistsException;
import com.csc.settlement.pojos.request.CreateUserRequest;
import com.csc.settlement.pojos.response.AuthenticationResponse;
import com.csc.settlement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(CreateUserRequest request) throws UserAlreadyExistsException {
        /* Username in DB is treated as Email Id*/
        if (repository.findByUsername(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        UserEntity savedUser = repository.save(UserEntity.builder()
                .mobile(request.getMobile())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        String jwtToken = jwtService.generateAccessToken(savedUser);
        return new AuthenticationResponse(jwtToken, "User registration was successful");
    }

    public AuthenticationResponse authenticate(CreateUserRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        UserEntity user = repository.findByUsername(request.getEmail()).orElseThrow(Exception::new);
        String jwtToken = jwtService.generateAccessToken(user);
        return new AuthenticationResponse(jwtToken, "User login was successful");
    }
}
