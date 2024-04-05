package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.model.Post;
import com.example.proptitendcoursepractice.repository.PostRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService{
    PostRepo postRepo;
    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepo.getAllPost();
    }

    @Override
    public List<Post> getPostByUsername(String username) {
        return postRepo.getPostByUsername(username);
    }

    @Override
    public Post getPostById(int postId) {
        return postRepo.getPostById(postId);
    }

    @Override
    public void addPost(Post post) {
        postRepo.addPost(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepo.updatePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepo.deletePost(post);
    }
}
