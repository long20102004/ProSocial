package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.Message;
import com.example.proptitendcoursepractice.model.User;
import com.example.proptitendcoursepractice.service.MessageService;
import com.example.proptitendcoursepractice.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(MessageService messageService, UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/init-messages/{currentId}-{receiverId}")
    public void initMessage(@DestinationVariable("receiverId") int receivedId, @DestinationVariable("currentId") int id) {
        List<Message> messages = messageService.getMessagesByReceiverId(receivedId, id);
        messagingTemplate.convertAndSend("/messages/" + id, messages);
    }


    @MessageMapping("/send-messages")
    public void sendMessage(@Payload Message message) {
        messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/messages/"+ message.getReceiver(), message);
    }



    @GetMapping("/messages/{receiverId}")
    public String oneToOneMessage(Model model, @PathVariable int receiverId) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("friends", userService.getAllUser(currentUser.getUsername()));
        model.addAttribute("user", currentUser);
        model.addAttribute("currentFriend", userService.findUserById(receiverId));
        return "detail-chat";
    }


    @GetMapping("/messages")
    public ModelAndView chatting() {
        ModelAndView modelAndView = new ModelAndView();
        User currentUser = userService.getCurrentUser();
        modelAndView.addObject("friends", userService.getAllUser(currentUser.getUsername()));
        modelAndView.addObject("user", currentUser);
        modelAndView.setViewName("chat-default-screen");
        return modelAndView;
    }
}