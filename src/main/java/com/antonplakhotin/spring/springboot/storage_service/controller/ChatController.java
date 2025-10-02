package com.antonplakhotin.spring.springboot.storage_service.controller;

import com.antonplakhotin.spring.springboot.storage_service.dto.*;
import com.antonplakhotin.spring.springboot.storage_service.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/testChat")
    public String test() {
        return "321";
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<ChatRes> getChat(@PathVariable long chatId) {
        Optional<ChatRes> chatRes = chatService.getChat(chatId);

        if (chatRes.isPresent()) {
            return ResponseEntity.ok(chatRes.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/chats/{userId}")
    public ResponseEntity<List<ChatRes>> getAllChats(@PathVariable String userId) {
        List<ChatRes> chats = chatService.getAllChats(userId);
        if (chats != null) {
            return ResponseEntity.ok(chats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/chat")
    public ResponseEntity<Long> createChat(@RequestBody CreateChatRq createChatRq) {
        System.out.println("Received create chat request: " + createChatRq.getUserId() + ", " + createChatRq.getTitle());
        long result = chatService.createChat(createChatRq);
        System.out.println("Created chat with ID: " + result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chat/setPrompt")
    public HttpStatus setPrompt(@RequestBody ChatPromptRq chatPromptRq) {
        boolean result = chatService.setPrompt(chatPromptRq);
        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }

    @PutMapping("/chat/rename")
    public HttpStatus renameChat(@RequestBody RenameChatRq renameChatRq) {
        boolean result = chatService.renameChat(renameChatRq);
        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }

    @DeleteMapping("/chat/delete/{chatId}")
    public HttpStatus deleteChat(@PathVariable long chatId) {
        boolean result = chatService.deleteChat(chatId);
        if (result) {
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}


