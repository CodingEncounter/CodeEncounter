package com.codeup.codeencounter.controllers;



import com.codeup.codeencounter.models.ChatMessage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/user/{username}/chat")
    public String chat() {
        return "user/chat";
    }

    @GetMapping("/user/{username}/ide")
    public String ide() {
        return "user/ide";
    }

    @GetMapping("/user/{username}/java")
    public String java() {
        return "user/java";
    }

    @GetMapping("/user/{username}/python")
    public String python() {
        return "user/python";
    }

    @GetMapping("/user/{username}/nodejs")
    public String nodejs() {
        return "user/nodejs";
    }

    @GetMapping("/user/{username}/c")
    public String c() {
        return "user/c";
    }
//    @RequestMapping("/{username}/chat")
//    public String index(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken))
//            return "redirect:/user/" + auth.getName();
//
//        return "user/chat";
//    }

//    @RequestMapping("/{username}/chat")
//    public String chat(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken))
//            return "redirect:/user/" + auth.getName();
//
//        return "/user/chat";
//    }

}


