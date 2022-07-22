package com.codeup.codeencounter.controllers;


import com.codeup.codeencounter.models.UserChat;
import com.codeup.codeencounter.repositories.UserChatRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserChatController{
    @Autowired
    UserChatRepo userChatRepo;

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserChat> createUserChat(@RequestBody UserChat user){
        return new ResponseEntity<>(userChatRepo.save(user), HttpStatus.OK);
    }
    @GetMapping(value = "/getUser")
    public ResponseEntity<UserChat> getUserChat(@RequestParam(required = true) Long userId){
        return new ResponseEntity<>(userChatRepo.findById(userId).get(), HttpStatus.OK);
    }
}