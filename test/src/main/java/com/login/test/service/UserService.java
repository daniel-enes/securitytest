package com.login.test.service;

import com.login.test.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto findByEmail(String email);
    UserDto findByUserId(String userId);
    UserDto create(UserDto user);

}
