package com.codeup.codeencounter.controllers;



import com.codeup.codeencounter.models.ChatMessage;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "/chat/chat";
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


