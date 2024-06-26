package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import com.example.proptitendcoursepractice.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService{
    public MessageRepo messageRepo;
    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo){
        this.messageRepo = messageRepo;
    }

    @Override
    public void saveMessage(Message message) {
        messageRepo.saveMessage(message);
    }

    @Override
    public List<Message> getMessagesByReceiverId(int id, int currentId) {
        return messageRepo.getMessagesByReceiverId(id, currentId);
    }

}
