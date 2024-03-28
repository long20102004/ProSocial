package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;

public interface MessageDao {
    public void saveMessage(Message message);
    public void loadAllMessage();
    public List<Message> getMessagesByConnection(String connection);
}
