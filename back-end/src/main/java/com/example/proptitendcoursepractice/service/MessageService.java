package com.example.proptitendcoursepractice.service;

import com.example.proptitendcoursepractice.dao.MessageDao;
import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Service;
public interface MessageService {
    public void saveMessage(Message message);
}
