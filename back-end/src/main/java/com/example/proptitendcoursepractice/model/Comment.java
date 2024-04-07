//package com.example.proptitendcoursepractice.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Table(name = "comment")
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Comment {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "content")
//    private String content;
//    @Column(name = "sender")
//    private int userId;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_post", referencedColumnName = "id")
//    private Post post;
//}
