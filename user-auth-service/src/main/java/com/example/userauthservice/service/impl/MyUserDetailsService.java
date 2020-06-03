package com.example.userauthservice.service.impl;

import com.example.userauthservice.entity.MyUserDetails;
import com.example.userauthservice.entity.User;
import com.example.userauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userRepository.findByUsername(username).ifPresent(user -> {
//            return new MyUserDetails(user);
//        });
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("No user fro: " + username));
        return optionalUser.map(MyUserDetails::new).get();
    }
}
