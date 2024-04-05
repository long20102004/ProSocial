package com.example.proptitendcoursepractice.controller;

import com.example.proptitendcoursepractice.model.Post;
import com.example.proptitendcoursepractice.model.Reaction;
import com.example.proptitendcoursepractice.service.PostService;
import com.example.proptitendcoursepractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000")

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

//    @GetMapping("")
//    public ResponseEntity<Map<String, Object>> webMainPage() {
//        Map<String, Object> response = new HashMap<>();
//        String currentUsername = userService.getCurrentUsername();
//        response.put("userList", userService.getAllUser(currentUsername));
//        response.put("currentUser", userService.getCurrentUser());
//        response.put("postList", postService.getAllPost());
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/post")
    public ModelAndView handlePost(@RequestParam("input-post") String content, @RequestParam("image") MultipartFile image) throws IOException {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        Post post = new Post(content, formatter.format(currentTime));
        post.setOwner(userService.getCurrentUser());

        String imagePath;
        if (!image.isEmpty()) {
            byte[] bytes = image.getBytes();
            imagePath = "src/main/resources/static/media/" + image.getOriginalFilename();
            Path path = Paths.get(imagePath);
            Files.write(path, bytes);
            post.setAttachedResources("/media/" + image.getOriginalFilename());
        }

        postService.addPost(post);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/delete-post/{postId}")
    public ModelAndView deletePost(@PathVariable("postId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return modelAndView;
    }

    @PostMapping("/update-post/{postId}")
    public ModelAndView updatePost(@PathVariable("postId") int id, @RequestParam("updatedContent") String content) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Post post = postService.getPostById(id);
        post.setContent(content);
        postService.updatePost(post);
        return modelAndView;
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> reactToPost(@PathVariable("postId") int postId, @RequestBody Reaction reaction) {
        Post currentPost = postService.getPostById(postId);
        reaction.setPost(currentPost);
        currentPost.addReaction(reaction);
        postService.updatePost(currentPost);
        return new ResponseEntity<>("Reaction succeeded", HttpStatus.OK);
    }
}
