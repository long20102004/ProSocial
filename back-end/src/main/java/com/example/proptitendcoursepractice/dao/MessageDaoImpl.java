package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Message> getMessagesByConnection(String connection) {
        TypedQuery<Message> messageTypedQuery = entityManager.createQuery("SELECT u FROM Message u where u.connection = :connection and u.type = 'CHAT' order by u.id asc ", Message.class);
        messageTypedQuery.setParameter("connection", connection);
        return messageTypedQuery.getResultList();
    }
}
