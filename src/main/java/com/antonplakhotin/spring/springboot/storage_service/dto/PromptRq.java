package com.antonplakhotin.spring.springboot.storage_service.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromptRq {
    private String title;
    private String authorId;
    private String description;
    private String text;
}
