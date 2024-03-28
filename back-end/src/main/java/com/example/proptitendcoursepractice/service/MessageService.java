package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {
    public void saveMessage(Message message);
    public List<Message> getMessagesByConnection(String connection);
}
