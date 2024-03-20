package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController{
    @MessageMapping("/send-message")
    @SendTo("/messages")
    public String sendMessage(@Payload String message){
        return message;
    }
}