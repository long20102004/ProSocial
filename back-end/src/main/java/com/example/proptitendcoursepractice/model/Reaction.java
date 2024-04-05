package com.example.proptitendcoursepractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "reaction")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "post_id")
    private int postId;
    @Column(name = "count")
    private int count;
    @Column(name = "user_id")
    private int userId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_fk", referencedColumnName = "id")
    private Post post;
}
