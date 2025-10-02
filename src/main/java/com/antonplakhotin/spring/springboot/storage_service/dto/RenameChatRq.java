package com.antonplakhotin.spring.springboot.storage_service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RenameChatRq {
    private long chatId;
    private String title;
}
