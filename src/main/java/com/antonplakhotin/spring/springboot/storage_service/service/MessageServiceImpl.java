package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRq;
import com.antonplakhotin.spring.springboot.storage_service.entity.Message;
import com.antonplakhotin.spring.springboot.storage_service.repository.MessageRepository;
import com.antonplakhotin.spring.springboot.storage_service.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository repository = new MessageRepositoryImpl();

    @Override
    public long createMessage(MessageRq messageRq) {
        return repository.createMessage(messageRq);
    }

    @Override
    public List<MessageRes> getLastMessages(Long chatId, Long count) {
        List<Message> messages = repository.getLastMessages(chatId, count);
        List<MessageRes> lastMessage = messages.stream()
                .map(message -> MessageRes.builder()
                        .messageId(message.getMessageId())
                        .chatId(message.getChatId())
                        .author(message.getAuthor())
                        .content(message.getContent())
                        .build()).collect(Collectors.toList());
        return lastMessage;

    }

    @Override
    public List<MessageRes> getAllMessage(long chatId) {
        List<Message> messages = repository.getAllMessage(chatId);
        List<MessageRes> allMessage = messages.stream()
                .map(message -> MessageRes.builder()
                        .messageId(message.getMessageId())
                        .chatId(message.getChatId())
                        .author(message.getAuthor())
                        .content(message.getContent())
                        .build()).collect(Collectors.toList());
        return allMessage;
    }

    @Override
    public boolean deleteMessage(long chatId, long messageId) {
        return repository.deleteMessage(chatId, messageId);
    }
}
