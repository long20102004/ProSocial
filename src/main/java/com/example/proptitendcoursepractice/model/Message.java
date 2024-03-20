package com.example.proptitendcoursepractice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Message {
    private String content;
    private String sender;
    public TypeMessage type;
    public enum TypeMessage {
        CHAT, JOIN, LEAVE;
    }
}
