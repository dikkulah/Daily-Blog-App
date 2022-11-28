package com.dikkulah.business.services;

import com.dikkulah.business.dto.LoginRequest;
import com.dikkulah.business.dto.LoginResponse;
import com.dikkulah.exception.WrongEmailOrPasswordException;
import com.dikkulah.security.UserDetailsServiceCustom;
import com.dikkulah.security.UserPrincipal;
import com.dikkulah.security.jwt.JwtProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;
    private final UserService userService;


    public LoginResponse loginReturnJwt(LoginRequest loginRequest) {
        userService.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("register.userNotFound"));
        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            token = jwtProvider.generateToken(userPrincipal);
        } catch (Exception e) {
            throw new WrongEmailOrPasswordException();
        }
        return new LoginResponse(token);


    }

    public String validateToken(String token) {
        String baseToken = token.substring(7);
        String username =jwtProvider.getUsernameFromToken(baseToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals(username))
        {
            return jwtProvider.validateToken(baseToken,(UserDetails) authentication.getPrincipal())
                    ? username : null;
        } throw new RuntimeException("Error validateToken");

    }
}