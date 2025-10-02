package com.antonplakhotin.spring.springboot.storage_service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatPromptRq {
    private long chatId;
    private long promptId;
}
