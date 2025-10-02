package com.antonplakhotin.spring.springboot.storage_service.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetPromptRq {
    private String chatId;
    private String promptId;
}
