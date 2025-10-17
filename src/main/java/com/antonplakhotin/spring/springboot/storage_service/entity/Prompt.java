package com.antonplakhotin.spring.springboot.storage_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prompt")
public class Prompt {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "authorId")
    private String authorId;

    @Column(name = "description")
    private String description;

    @Column(name = "text")
    private String text;

    public Prompt(String title, String authorId, String description, String text) {
        this.title = title;
        this.authorId = authorId;
        this.description = description;
        this.text = text;
    }

}
