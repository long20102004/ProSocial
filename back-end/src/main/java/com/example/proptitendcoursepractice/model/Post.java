package com.example.proptitendcoursepractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Post")
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "owner")
    private String owner;
    @Column(name = "timeStamp")
    private String timeStamp;
    @Column(name = "attached_resources")
    private String attachedResources;
}
