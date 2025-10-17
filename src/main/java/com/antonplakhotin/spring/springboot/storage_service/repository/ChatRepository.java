package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.Prompt;


import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    public Optional<Chat> getChat(Long chatId);

    public List<Chat> getAllChats(String userId);

    public Prompt getPrompt(long chatId);

    public long createChat(CreateChatRq createChatRq);

    public boolean setPrompt(ChatPromptRq chatPromptRq);

    public boolean renameChat(RenameChatRq renameChatRq);

    public boolean deleteChat(long chatId);
}
