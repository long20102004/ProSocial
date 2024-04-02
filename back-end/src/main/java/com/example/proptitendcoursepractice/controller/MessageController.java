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

    @MessageMapping("/init-messages/{senderId}-{receiverId}")
    public void initMessage(@DestinationVariable("senderId") String id,
                            @DestinationVariable("receiverId") String receivedId) {
        String connection = id + "-" + receivedId;
        List<Message> messages = messageService.getMessagesByConnection(connection);
        messagingTemplate.convertAndSend("/init-chat", messages);
    }


    @MessageMapping("/send-messages/{senderId}-{receiverId}")
    public void sendMessage(@Payload Message message, @DestinationVariable("senderId") String id,
                            @DestinationVariable("receiverId") String receivedId) {
        messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/messages/" + id + '-' + receivedId, message);
    }



    @GetMapping("/messages/{senderId}-{receiverId}")
    public String oneToOneMessage(Model model, @PathVariable int receiverId, @PathVariable int senderId) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("friends", userService.getAllUser(currentUser.getUsername()));
        model.addAttribute("user", currentUser);
        int friendId = receiverId;
        if (receiverId == currentUser.getId()) friendId = senderId;
        model.addAttribute("currentFriend", userService.findUserById(friendId));
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