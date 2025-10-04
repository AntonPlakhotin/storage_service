package com.antonplakhotin.spring.springboot.storage_service.service;

import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRq;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MessageService {

    public long createMessage(MessageRq messageRq);

    public List<MessageRes> getLastMessages(Long chatId, Long count);

    public List<MessageRes> getAllMessage(long chatId);

    public boolean deleteMessage(long chatId, long messageId);


}

