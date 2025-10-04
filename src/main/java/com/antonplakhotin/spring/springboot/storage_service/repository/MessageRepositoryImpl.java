package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
   EntityManager entityManager;

    @Override
    @Transactional
    public long createMessage(MessageRq messageRq) {
        Message message = new Message();
        message.setChatId(messageRq.getChatId());
        message.setAuthor(messageRq.getAuthor());
        message.setContent(messageRq.getContent());

        entityManager.persist(message);

        return message.getMessageId();
    }

    @Override
    @Transactional
    public List<Message> getLastMessages(Long chatId, Long count) {
        if (chatId == null || count == null || count <= 0) {
            return new ArrayList<>();
        }

        int maxResults = Math.min(count.intValue(), 1000);

        return entityManager.createQuery(
                        "SELECT m FROM Message m WHERE m.chatId = :chatId ORDER BY m.messageId DESC",
                        Message.class)
                .setParameter("chatId", chatId)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Message> getAllMessage(long chatId) {
        return entityManager.createQuery("SELECT m FROM Message m WHERE m.chatId = :chatId", Message.class)
                .setParameter("chatId", chatId).getResultList();
    }

    @Override
    @Transactional
    public boolean deleteMessage(long chatId, long messageId) {
        Message message = entityManager.createQuery(
                        "SELECT m FROM Message m WHERE m.messageId = :messageId AND m.chatId = :chatId",
                        Message.class)
                .setParameter("messageId", messageId)
                .setParameter("chatId", chatId)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (message != null) {
            entityManager.remove(message);
            return true;
        }
        return false;
    }
}

