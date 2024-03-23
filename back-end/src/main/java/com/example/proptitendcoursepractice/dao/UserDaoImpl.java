package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.exception.UserExistedException;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> userTypedQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
        userTypedQuery.setParameter(1, username);
        List<User> users = userTypedQuery.getResultList();
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    @Transactional
    public void saveNewUser(UserDto userDto) {
        User user = findUserByUsername(userDto.getUsername());
        if (user != null) {
            throw new UserExistedException("user existed");
        } else {
            User newUser = new User(userDto.getUsername(), userDto.getPassword(), userDto.getRoles());
            entityManager.persist(newUser);
        }
    }

    @Override
    public List<User> getAllUser() {
        TypedQuery<User>userTypedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
        return userTypedQuery.getResultList();
    }
}