package com.dikkulah.ui.api;

import com.dikkulah.business.dto.LoginRequest;
import com.dikkulah.business.dto.LoginResponse;
import com.dikkulah.business.dto.RegisterRequest;
import com.dikkulah.business.dto.RegisterResponse;
import com.dikkulah.business.services.AuthenticationService;
import com.dikkulah.business.services.UserService;
import com.dikkulah.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtProvider jwtProvider;


    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userService.createUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authenticationService.loginReturnJwt(loginRequest), HttpStatus.OK);
    }

    @PostMapping("validateToken")
    public ResponseEntity<String> validate(@RequestBody String token) {
        System.out.println(token);
        var result = authenticationService.validateToken(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
