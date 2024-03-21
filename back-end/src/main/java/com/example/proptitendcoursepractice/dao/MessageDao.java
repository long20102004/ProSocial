package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;

public interface MessageDao {
    public void saveMessage(Message message);
    public void loadAllMessage();
}
