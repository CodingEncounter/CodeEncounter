package com.codeup.codeencounter.controllers;


import com.codeup.codeencounter.models.UserChat;
import com.codeup.codeencounter.repositories.UserChatRepo;
import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserChatController {
    @Autowired
    UserChatRepo.UserRepository userRepository;

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserChat> createUserChat(@RequestBody User user){
       return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
    @GetMapping(value = "/getUser")
    public ResponseEntity<UserChat> getUser(@RequestParam(required = true) Long userId){
        return new ResponseEntity<>(userRepository.findById(userId).get(), HttpStatus.OK);
    }
}
