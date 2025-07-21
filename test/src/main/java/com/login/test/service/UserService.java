package com.login.test.service;

import com.login.test.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto find(String email);
    UserDto create(UserDto user);

}
