package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public MessageDao messageDao;
    public MessageService(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }
}
