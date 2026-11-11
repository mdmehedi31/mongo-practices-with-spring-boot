package com.mongopractices.service;


import com.mongopractices.entity.UserEntity;
import com.mongopractices.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserEntity createUser(UserEntity user) {
        log.info("Creating new user : {}", user.getEmail());

        UserEntity createdUser = userRepository.save(user);
        log.info("Created new user : {},  {}", createdUser.getEmail(),createdUser.getId());
        return createdUser;
    }

    public UserEntity getUser(ObjectId id) {
        return userRepository.getUserEntityById(id);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
