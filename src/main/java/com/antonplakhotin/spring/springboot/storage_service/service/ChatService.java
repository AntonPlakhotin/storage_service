package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    public Optional<ChatRes> getChat(Long chatId);

    public List<ChatRes> getAllChats(String userId);

    public PromptRes getPrompt(Long chatId);

    public long createChat(CreateChatRq createChatRq);

    public boolean setPrompt(ChatPromptRq chatPromptRq);

    public boolean renameChat(RenameChatRq renameChatRq);

    public boolean deleteChat(long chatId);

}
