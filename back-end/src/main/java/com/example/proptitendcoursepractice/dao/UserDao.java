package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.User;

import java.util.List;

public interface UserDao {
    public User findUserByUsername(String username);
    public void saveNewUser(UserDto userDto);
    public List<User>getAllUser(String currentUsername);
    public User getUserById(int id);
}
