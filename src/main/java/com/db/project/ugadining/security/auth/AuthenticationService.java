package com.db.project.ugadining.security.auth;

import com.db.project.ugadining.exception.AlreadyExistsException;
import com.db.project.ugadining.security.user.Role;
import com.db.project.ugadining.security.user.User;
import com.db.project.ugadining.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.security.auth.login.FailedLoginException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger( AuthenticationService.class );
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        logger.info("Registering a new user: {}", registerRequest.getEmail());

        if ( userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AlreadyExistsException(registerRequest.getEmail());
        }

        var user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        logger.info("User registered successfully: {}", user.getEmail());

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws FailedLoginException {
        try {
            logger.info("Authenticating user: {}", authenticationRequest.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );

            var user = userRepository
                    .findUserByEmail(authenticationRequest.getEmail())
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User not found")
                    );

            logger.info("User authenticated successfully: {}", user.getEmail());

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .email(user.getEmail())
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            logger.error("Error during authentication: {}", e.getMessage());
            throw new FailedLoginException("Error during authentication");
        }
    }
}

