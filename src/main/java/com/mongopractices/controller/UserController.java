package com.mongopractices.controller;


import com.mongopractices.entity.UserEntity;
import com.mongopractices.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("/get/{userId}")
    public ResponseEntity<UserEntity> gerUserById(@PathVariable ObjectId userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

}
