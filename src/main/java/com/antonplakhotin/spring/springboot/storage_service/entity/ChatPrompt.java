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
@Table(name = "chat_prompt")
@IdClass(ChatPromptId.class)
public class ChatPrompt {

    @Id
    @Column(name = "chat_id")
    private long chatId;

    @Id
    @Column(name = "prompt_id")
    private long promptId;
}
