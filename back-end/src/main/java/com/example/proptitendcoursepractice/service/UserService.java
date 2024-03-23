package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dto.UserDto;
import com.example.proptitendcoursepractice.model.Friend;
import com.example.proptitendcoursepractice.model.User;

import java.util.List;

public interface UserService {
    public User findUserByUsername(String username);
    public void registerNewUser(UserDto userDto);
    public String getCurrentUsername();
    public User getCurrentUser();
}
