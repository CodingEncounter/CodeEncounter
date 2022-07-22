package com.codeup.codeencounter.controllers;


import com.codeup.codeencounter.models.ChatMessage;
import com.codeup.codeencounter.repositories.UserChatRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
//@RestController
@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
//    @Autowired
//    UserChatRepo userChatRepo;
//
//    @PostMapping(value = "/createUser")
//    public ResponseEntity<ChatMessage> createUserChat(@RequestBody ChatMessage user){
//       return new ResponseEntity<>(userChatRepo.save(user), HttpStatus.OK);
//    }
//    @GetMapping(value = "/getUser")
//    public ResponseEntity<ChatMessage> getUserChat(@RequestParam(required = true) Long userId){
//        return new ResponseEntity<>(userChatRepo.findById(userId).get(), HttpStatus.OK);
//    }
//}