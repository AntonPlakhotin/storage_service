package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.ChatPrompt;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ChatRepositoryImpl implements ChatRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Chat> getChat(Long chatId) {
        return Optional.ofNullable(entityManager.find(Chat.class, chatId));
    }

    @Override
    public List<Chat> getAllChats(String userId) {
        return entityManager.createQuery("select c from Chat c WHERE c.userId = :userId", Chat.class).getResultList();
    }

    @Override
    @Transactional
    public long createChat(CreateChatRq createChatRq) {
        Chat chat = new Chat();
        chat.setUserId(createChatRq.getUserId());
        chat.setTitle(createChatRq.getTitle());

        System.out.println("Before persist - Chat: " + chat.getUserId() + ", " + chat.getTitle());
        entityManager.persist(chat);
        System.out.println("After persist - Chat ID: " + chat.getId());

        return chat.getId();
    }

    @Override
    @Transactional
    public boolean setPrompt(ChatPromptRq chatPromptRq) {
        Chat chat = entityManager.find(Chat.class, chatPromptRq.getChatId());
        if (chat == null) {
            return false;
        }

        Prompt prompt = entityManager.find(Prompt.class, chatPromptRq.getPromptId());
        if (prompt == null) {
            return false;
        }

        ChatPrompt chatPrompt = new ChatPrompt();
        chatPrompt.setChatId(chatPromptRq.getChatId());
        chatPrompt.setPromptId(chatPromptRq.getPromptId());
        entityManager.persist(chatPrompt);

        return true;
    }



    @Override
    @Transactional
    public boolean renameChat(RenameChatRq renameChatRq) {
        Chat chat = entityManager.find(Chat.class, renameChatRq.getChatId());
        if (chat != null) {
            chat.setTitle(renameChatRq.getTitle());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteChat(long chatId) {
        Chat chat = entityManager.find(Chat.class, chatId);
        if (chat != null) {
            entityManager.remove(chat);
            return true;
        } else {
            return false;
        }
    }

}
