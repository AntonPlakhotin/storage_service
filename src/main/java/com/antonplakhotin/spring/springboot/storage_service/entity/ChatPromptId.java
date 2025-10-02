package com.antonplakhotin.spring.springboot.storage_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatPromptId implements Serializable {
    private long chatId;
    private long promptId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatPromptId that = (ChatPromptId) o;
        return chatId == that.chatId && promptId == that.promptId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, promptId);
    }
}