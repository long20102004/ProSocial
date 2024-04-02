package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PostDaoImpl implements PostDao{
    EntityManager entityManager;
    public PostDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Post> getAllPost() {
        TypedQuery<Post> postTypedQuery = entityManager.createQuery("SELECT u FROM Post u order by u.id asc ", Post.class);
        return postTypedQuery.getResultList();
    }

    @Override
    public List<Post> getPostByUsername(String username) {
        TypedQuery<Post> postTypedQuery = entityManager.createQuery("SELECT u FROM Post u where u.owner = :username order by u.id asc ", Post.class);
        postTypedQuery.setParameter("username", username);
        return postTypedQuery.getResultList();
    }

    @Override
    public Post getPostById(int postId) {
        TypedQuery<Post> postTypedQuery = entityManager.createQuery("SELECT u FROM Post u WHERE u.id = :id", Post.class);
        postTypedQuery.setParameter("id", postId);
        return postTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void addPost(Post post) {
        entityManager.persist(post);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    @Override
    @Transactional
    public void deletePost(Post post) {
        entityManager.remove(post);
    }
}
