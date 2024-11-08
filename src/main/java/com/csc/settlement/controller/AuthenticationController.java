package com.csc.settlement.controller;

import com.csc.settlement.exception.UserAlreadyExistsException;
import com.csc.settlement.pojos.request.CreateUserRequest;
import com.csc.settlement.pojos.response.AuthenticationResponse;
import com.csc.settlement.service.impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateUserRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody CreateUserRequest request) throws Exception {
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
