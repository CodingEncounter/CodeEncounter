package com.codeup.codeencounter.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.codeup.codeencounter.models.ChatMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
    @RequestMapping("/chat")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
            return "redirect:/user/" + auth.getName();

        return "chat";
    }
}

//
//import java.util.Optional;
//
//import com.codeup.codeencounter.repositories.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.codeup.codeencounter.models.ChatUser;
//
//@CrossOrigin("*")
//@RestController
//@Controller
//public class ChatController {
//
//    public UserRepo userRepo;
//
//    public ChatController(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    @Autowired
//    ChatRepo chatRepo;
//
//    @PostMapping(value = "/createUser")
//    public ResponseEntity<ChatUser> createUser(@RequestBody ChatUser chatUser) {
//        return new ResponseEntity<>(chatRepo.save(chatUser), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/getUser")
//    public ResponseEntity<ChatUser> getUser(@RequestParam(required = true) Long userId) {
//        return new ResponseEntity<>(chatRepo.findById(userId).get(), HttpStatus.OK);
//    }
//}
