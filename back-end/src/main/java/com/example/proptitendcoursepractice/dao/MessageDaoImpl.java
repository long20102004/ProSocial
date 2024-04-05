package com.example.proptitendcoursepractice.dao;

import com.example.proptitendcoursepractice.model.Message;
import com.example.proptitendcoursepractice.service.UserService;
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
    @Transactional
    public List<Message> getMessagesByReceiverId(int id, int currentId) {
        TypedQuery<Message> messageTypedQuery = entityManager.createQuery("SELECT m from Message m where (m.receiver = :id and m.sender = :currentId) or (m.sender = :id and m.receiver = :currentId)  order by m.id", Message.class);
        messageTypedQuery.setParameter("id", id);
        messageTypedQuery.setParameter("currentId", currentId);
        return messageTypedQuery.getResultList();
    }
}
