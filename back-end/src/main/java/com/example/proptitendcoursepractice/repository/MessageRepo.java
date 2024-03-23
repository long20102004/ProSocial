package com.example.proptitendcoursepractice.repository;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepo {
    MessageDao messageDao;
    public MessageRepo(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }
}
