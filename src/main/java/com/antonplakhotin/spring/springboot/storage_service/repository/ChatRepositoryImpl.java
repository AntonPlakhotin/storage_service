package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.ChatPrompt;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    @PersistenceContext
    private EntityManager entityChatManager;

    @Override
    public Optional<Chat> getChat(Long chatId) {
        return Optional.ofNullable(entityChatManager.find(Chat.class, chatId));
    }

    @Override
    public List<Chat> getAllChats(String userId) {
        return entityChatManager.createQuery("SELECT c FROM Chat c WHERE c.userId = :userId", Chat.class)
                .setParameter("userId", userId).getResultList();
    }

    @Override
    public Prompt getPrompt(long chatId) {
        ChatPrompt chatPrompts = entityChatManager.createQuery(
                        "SELECT cp FROM ChatPrompt cp WHERE cp.chatId = :chatId", ChatPrompt.class)
                .setParameter("chatId", chatId).getSingleResult();

        if (chatPrompts == null) {
            System.out.println("No ChatPrompt found for chatId: " + chatId);
            return null;
        }
        Long promptId = chatPrompts.getPromptId();
        System.out.println("Found promptId: " + promptId + " for chatId: " + chatId);
        Prompt prompt = entityChatManager.find(Prompt.class, promptId);
        if (prompt == null) {
            System.out.println("Prompt not found with id: " + promptId);
            return null;
        }
        System.out.println("Found prompt: " + prompt.getTitle());
        return prompt;
        /*ChatPrompt chatPrompt = entityChatManager.find(ChatPrompt.class, chatId);
        return entityChatManager.find(Prompt.class, chatPrompt.getPromptId());*/
    }

    @Override
    @Transactional
    public long createChat(CreateChatRq createChatRq) {
        Chat chat = new Chat();
        chat.setUserId(createChatRq.getUserId());
        chat.setTitle(createChatRq.getTitle());

        entityChatManager.persist(chat);

        return chat.getId();
    }

    @Override
    @Transactional
    public boolean setPrompt(ChatPromptRq chatPromptRq) {
        Chat chat = entityChatManager.find(Chat.class, chatPromptRq.getChatId());
        if (chat == null) {
            return false;
        }

        Prompt prompt = entityChatManager.find(Prompt.class, chatPromptRq.getPromptId());
        if (prompt == null) {
            return false;
        }

        ChatPrompt chatPrompt = new ChatPrompt();
        chatPrompt.setChatId(chatPromptRq.getChatId());
        chatPrompt.setPromptId(chatPromptRq.getPromptId());
        entityChatManager.persist(chatPrompt);

        return true;
    }

    @Override
    @Transactional
    public boolean renameChat(RenameChatRq renameChatRq) {
        Chat chat = entityChatManager.find(Chat.class, renameChatRq.getChatId());
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
        Chat chat = entityChatManager.find(Chat.class, chatId);
        if (chat != null) {
            entityChatManager.remove(chat);
            return true;
        }
            return false;
    }

}
