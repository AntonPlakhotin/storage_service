package com.antonplakhotin.spring.springboot.storage_service.controller;

import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRes;
import com.antonplakhotin.spring.springboot.storage_service.dto.MessageRq;
import com.antonplakhotin.spring.springboot.storage_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<Long> createMessage(@RequestBody MessageRq messageRq) {
        long result = messageService.createMessage(messageRq);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{chatId}/last_messages")
    public ResponseEntity<List<MessageRes>> getLastMessages(@PathVariable long chatId,
                                                            @RequestBody long count) {
        List<MessageRes> lastMessages = messageService.getLastMessages(chatId, count);
        return lastMessages != null ? ResponseEntity.ok(lastMessages) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageRes>> getAllMessages(@PathVariable long chatId) {
        List<MessageRes> allMessages = messageService.getAllMessage(chatId);
        return allMessages != null ? ResponseEntity.ok(allMessages) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{chatId}/messages")
    public HttpStatus deleteMessage(@PathVariable long chatId,
                                    @RequestBody long messageId) {
        boolean result = messageService.deleteMessage(chatId, messageId);
        return result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
    }
}
