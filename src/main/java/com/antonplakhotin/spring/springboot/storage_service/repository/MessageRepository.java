package com.antonplakhotin.spring.springboot.storage_service.repository;

import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Chat;
import com.antonplakhotin.spring.springboot.storage_service.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    public long createMessage(MessageRq messageRq);

    public List<Message> getLastMessages(Long chatId, Long count);

    public List<Message> getAllMessage(long chatId);

    public boolean deleteMessage(long chatId, long messageId);


}
