package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.User;
import com.example.proptitendcoursepractice.service.MessageService;
import com.example.proptitendcoursepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes("user")
public class UserController {
    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login-page");
        return modelAndView;
    }
    @GetMapping("/messages")
    public ModelAndView chatting(){
        ModelAndView modelAndView = new ModelAndView();
        User currentUser = userService.getCurrentUser();
        modelAndView.addObject("friendList",currentUser.getFriends());
        modelAndView.addObject("user", userService.getCurrentUser() );
        modelAndView.setViewName("chat");
        return modelAndView;
    }
    @GetMapping("/sign-up")
    public ModelAndView signUp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new UserDto());
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }
    @PostMapping("/register-new-user")
    public ModelAndView registerNewUser(@ModelAttribute("newUser") UserDto user) {
        userService.registerNewUser(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }
}