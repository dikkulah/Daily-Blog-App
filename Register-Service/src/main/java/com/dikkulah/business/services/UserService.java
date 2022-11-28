package com.dikkulah.business.services;

import com.dikkulah.business.dto.RegisterRequest;
import com.dikkulah.business.dto.RegisterResponse;
import com.dikkulah.data.entity.User;
import com.dikkulah.data.repository.UserRepository;
import com.dikkulah.exception.EmailNotUniqueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public RegisterResponse createUser(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent())
            throw new EmailNotUniqueException(registerRequest.getEmail());
        else {
            userRepository.save(new User(registerRequest.getEmail(), registerRequest.getName(), registerRequest.getSurname(), passwordEncoder.encode(registerRequest.getPassword())));
            return new RegisterResponse("Register Succeed");
        }
    }


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
