package com.example.proptitendcoursepractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @GetMapping("/login")
    public RedirectView login(){
        return new RedirectView("/login-page.html");
    }
}