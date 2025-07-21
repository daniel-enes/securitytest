package com.login.test.service.impl;

import com.login.test.io.repository.UserRepository;
import com.login.test.service.UserService;
import com.login.test.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDto find(String email) {
        return null;
    }

    @Override
    public UserDto create(UserDto user) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
