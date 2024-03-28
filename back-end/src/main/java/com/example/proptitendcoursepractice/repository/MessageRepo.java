package com.example.proptitendcoursepractice.repository;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepo {
    MessageDao messageDao;
    public MessageRepo(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    public void saveMessage(Message message){
        messageDao.saveMessage(message);
    }
    public List<Message> getMessagesByConnection(String connection){
        return messageDao.getMessagesByConnection(connection);
    }
}
