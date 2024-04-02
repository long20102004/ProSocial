package com.example.proptitendcoursepractice.repository;

import com.example.proptitendcoursepractice.dao.PostDao;
import com.example.proptitendcoursepractice.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepo{
    PostDao postDao;
    public PostRepo(PostDao postDao){
        this.postDao = postDao;
    }
    public List<Post> getAllPost() {
        return postDao.getAllPost();
    }

    public List<Post> getPostByUsername(String username) {
        return postDao.getPostByUsername(username);
    }

    public Post getPostById(int postId) {
        return postDao.getPostById(postId);
    }

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    public void deletePost(Post post) {
        postDao.deletePost(post);
    }
}
