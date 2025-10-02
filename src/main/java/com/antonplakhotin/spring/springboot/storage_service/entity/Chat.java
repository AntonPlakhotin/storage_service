package com.antonplakhotin.spring.springboot.storage_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "title")
    private String title;

    public Chat(String userId, String title) {
        this.userId = userId;
        this.title = title;
    }
}
