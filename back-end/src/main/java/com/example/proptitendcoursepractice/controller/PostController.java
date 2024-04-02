package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.model.Post;
import com.example.proptitendcoursepractice.service.PostService;
import com.example.proptitendcoursepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PostController {
    UserService userService;
    PostService postService;

    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("")
    public ModelAndView webMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        String currentUsername = userService.getCurrentUsername();
        modelAndView.addObject("userList", userService.getAllUser(currentUsername));
        modelAndView.addObject("currentUser", userService.getCurrentUser());
        modelAndView.addObject("postList", postService.getAllPost());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/post")
    public ModelAndView handlePost(@RequestParam("input-post") String content) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        Post post = new Post(content, formatter.format(currentTime));
        post.setOwner(userService.getCurrentUser());
        postService.addPost(post);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    @PostMapping("/delete-post/{postId}")
    public ModelAndView deletePost(@PathVariable("postId")int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return modelAndView;
    }
    @PostMapping("/update-post/{postId}")
    public ModelAndView updatePost(@PathVariable("postId")int id, @RequestParam("updatedContent")String content){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Post post = postService.getPostById(id);
        post.setContent(content);
        postService.updatePost(post);
        return modelAndView;
    }
}
