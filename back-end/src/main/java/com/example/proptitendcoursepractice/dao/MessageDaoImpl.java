package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl implements MessageDao{
    public EntityManager entityManager;
    public MessageDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void saveMessage(Message message) {
        entityManager.persist(message);
    }
    @Override
    public void loadAllMessage() {
    }
}
