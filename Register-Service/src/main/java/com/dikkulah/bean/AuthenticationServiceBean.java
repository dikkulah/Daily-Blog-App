package com.dikkulah.bean;

import com.dikkulah.business.services.AuthenticationService;
import com.dikkulah.business.services.UserService;
import com.dikkulah.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthenticationServiceBean {
    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Bean
    public AuthenticationService authenticationServiceMethodBean(){
        return new AuthenticationService(authenticationManager,jwtProvider,userService);
    }
}