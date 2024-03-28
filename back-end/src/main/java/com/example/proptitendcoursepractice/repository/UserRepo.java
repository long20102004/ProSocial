package com.example.proptitendcoursepractice.repository;
import com.example.proptitendcoursepractice.dao.UserDao;
import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    UserDao userDao;
    @Autowired
    public UserRepo(UserDao userDao){
        this.userDao = userDao;
    }
    public User findUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }
    public void saveNewUser(UserDto userDto){
        userDao.saveNewUser(userDto);
    }
    public List<User> getAllUser(String currentUsername){
        return userDao.getAllUser(currentUsername);
    }
    public User getUserById(int id){
        return userDao.getUserById(id);
    }
}
