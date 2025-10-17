package com.antonplakhotin.spring.springboot.storage_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long messageId;

    @Column(name = "chat_id")
    private long chatId;

    @Enumerated(EnumType.STRING)
    @Column(name = "author")
    private Author author;

    @Column(name = "content")
    private String content;

    public Message(long chatId, Author author, String content) {
        this.chatId = chatId;
        this.author = author;
        this.content = content;
    }
}
