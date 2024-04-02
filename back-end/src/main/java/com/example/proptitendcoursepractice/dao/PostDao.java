package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Post;

import java.util.List;

public interface PostDao {
    public List<Post>getAllPost();
    public List<Post>getPostByUsername(String username);
    public Post getPostById(int postId);
    public void addPost(Post post);
    public void updatePost(Post post);
    public void deletePost(Post post);
}
