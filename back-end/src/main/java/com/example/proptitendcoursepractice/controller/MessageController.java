package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.Message;
import com.example.proptitendcoursepractice.model.User;
import com.example.proptitendcoursepractice.service.MessageService;
import com.example.proptitendcoursepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8088")
@Controller public class MessageController {
    public MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @MessageMapping("/send-message")
    @SendTo("/messages")
    public Message sendMessage(@Payload Message message) {
        messageService.saveMessage(message);
        return message;
    }


}