package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import org.springframework.stereotype.Component;
public interface MessageDao {
    public void saveMessage(Message message);
    public void loadAllMessage();
}
