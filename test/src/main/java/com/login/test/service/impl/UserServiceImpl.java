package com.login.test.service.impl;

import com.login.test.exception.UserServiceException;
import com.login.test.io.entity.UserEntity;
import com.login.test.io.repository.UserRepository;
import com.login.test.service.UserService;
import com.login.test.shared.Utils;
import com.login.test.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public UserDto findByUserId(String userId) {
        return null;
    }

    @Override
    public UserDto create(UserDto user) {

        UserEntity userFound= userRepository.findByEmail(user.getEmail());

        if(userFound != null) {
            throw new UserServiceException("Record already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        //Generate a public userId to be used as returning a response
        String publicUserId = utils.generateUserId(30);

        //Define the values to userEntity
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUser = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            throw new UsernameNotFoundException("User not found with email: "+ email);
        }

        // Debug output
        System.out.println("Loaded user - email: " + userEntity.getEmail());
        System.out.println("Encrypted password exists: " +
                (userEntity.getEncryptedPassword() != null && !userEntity.getEncryptedPassword().isEmpty()));

        // Convert to UserDto which implements UserDetails
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }
}
