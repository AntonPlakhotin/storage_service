package com.antonplakhotin.spring.springboot.storage_service.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRes {
    private long chatId;
    private String userId;
    private String title;
}
