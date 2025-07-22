package com.login.test.ui.controller;

import com.login.test.service.UserService;
import com.login.test.shared.dto.UserDto;
import com.login.test.ui.model.request.UserDetailsRequestModel;
import com.login.test.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public String show(@PathVariable String id) {
        return "User called";
    }

    @PostMapping
    public UserRest create(@RequestBody UserDetailsRequestModel userDetails) {

        UserRest response = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.create(userDto);
        BeanUtils.copyProperties(createdUser, response);

        return response;
    }
}
