package com.example.proptitendcoursepractice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "message")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "sender")
    private String sender;
    @Column(name = "type")
    private String type;
    @Column(name = "connection")
    private String connection;
    @Column(name = "timestamp")
    private String timeStamp;
//    public enum TypeMessage {
//        CHAT, JOIN, LEAVE;
//    }
}
