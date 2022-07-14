package com.avature.service;

import com.avature.entity.User;
import com.avature.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public User addUser(User user){
        log.info("saving user details:");
        User savedUser = userRepository.save(user);
        log.info("user saved:"+savedUser.getId());
        return savedUser;
    }

}
