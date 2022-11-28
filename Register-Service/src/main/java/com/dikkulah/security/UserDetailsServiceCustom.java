package com.dikkulah.security;

import com.dikkulah.business.services.UserService;
import com.dikkulah.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//lombok
@RequiredArgsConstructor

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("No registered user with ".concat(email).concat(" email.")) );
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword());
    }
}
