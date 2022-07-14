package com.avature.controller;

import com.avature.entity.User;
import com.avature.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody @Valid User user){
        return userService.addUser(user);
    }
}
