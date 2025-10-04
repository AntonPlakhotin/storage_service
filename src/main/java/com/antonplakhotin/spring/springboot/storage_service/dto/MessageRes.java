package com.antonplakhotin.spring.springboot.storage_service.dto;

import com.antonplakhotin.spring.springboot.storage_service.entity.Author;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRes {
    private long messageId;
    private long chatId;
    private Author author;
    private String content;
}
