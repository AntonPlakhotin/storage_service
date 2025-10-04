package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.repository.ChatRepository;
import com.antonplakhotin.spring.springboot.storage_service.repository.ChatRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repository;

    @Override
    public Optional<ChatRes> getChat(Long chatId) {
        return repository.getChat(chatId)
                .map(chat -> ChatRes.builder()
                        .id(chat.getId())
                        .userId(chat.getUserId())
                        .title(chat.getTitle())
                        .build());
    }

    @Override
    public List<ChatRes> getAllChats(String userId) {
        List<Chat> chats = repository.getAllChats(userId);
        List<ChatRes> chatResList = chats.stream()
                .map(chat -> ChatRes.builder()
                        .id(chat.getId())
                        .userId(chat.getUserId())
                        .title(chat.getTitle())
                        .build()).collect(Collectors.toList());
        return chatResList;
    }

    @Override
    public long createChat(CreateChatRq createChatRq) {
        return repository.createChat(createChatRq);
    }

    @Override
    public boolean setPrompt(ChatPromptRq chatPromptRq) {
        return repository.setPrompt(chatPromptRq);
    }

    @Override
    public boolean renameChat(RenameChatRq renameChatRq) {
        return repository.renameChat(renameChatRq);
    }

    @Override
    public boolean deleteChat(long chatId) {
        return repository.deleteChat(chatId);
    }
}
