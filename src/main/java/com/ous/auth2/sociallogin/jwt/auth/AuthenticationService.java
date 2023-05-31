package com.ous.auth2.sociallogin.jwt.auth;

import com.ous.auth2.sociallogin.jwt.config.JwtService;
import com.ous.auth2.sociallogin.jwt.repo.UserRepository;
import com.ous.auth2.sociallogin.jwt.user.entities.Roles;
import com.ous.auth2.sociallogin.jwt.user.entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerUser(RegisterRequest register) {
        var user = Users.builder()
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getEmail())
                .password(passwordEncoder.encode(register.getPassword()))
                .roles(Roles.USER)
                .build();
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .token(jwtService.generateTokenWithoutExtractClaims(user))
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authResponse) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authResponse.getEmail(),
                        authResponse.getPassword()));

        var user=userRepository.findByEmail(authResponse.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("Sorry, user email not found"));
        return AuthenticationResponse
                .builder()
                .token(jwtService.generateTokenWithoutExtractClaims(user))
                .build();
    }
}
