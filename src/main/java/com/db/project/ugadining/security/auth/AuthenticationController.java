package com.db.project.ugadining.security.auth;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger( AuthenticationController.class );
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        logger.info("Received registration request for email: {}", registerRequest.getEmail());
        AuthenticationResponse response = authenticationService.register(registerRequest);
        logger.info("Registration successful for email: {}", registerRequest.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws FailedLoginException {
        logger.info("Received authentication request for email: {}", authenticationRequest.getEmail());
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        logger.info("Authentication successful for email: {}", authenticationRequest.getEmail());
        return ResponseEntity.ok(response);
    }
}
