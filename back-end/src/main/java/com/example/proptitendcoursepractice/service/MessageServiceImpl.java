package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MessageServiceImpl implements MessageService{
    public MessageDao messageDao;
    @Autowired
    public MessageServiceImpl(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }
}
