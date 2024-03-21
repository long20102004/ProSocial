package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class MessageDaoImple implements MessageDao{
    public EntityManager entityManager;
    @Override
    public void saveMessage(Message message) {
        entityManager.persist(message);
    }
    @Override
    public void loadAllMessage() {
    }
}
