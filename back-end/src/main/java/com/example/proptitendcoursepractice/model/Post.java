package com.example.proptitendcoursepractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
    @Column(name = "time-stamp")
    private String timeStamp;
    @Column(name = "attached_resources")
    private String attachedResources;
    // default, fetch type is lazy, so it will not load the Many part to improve performance
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    List<Reaction> reactionList;
    public Post(String content, String timeStamp) {
        this.content = content;
        this.timeStamp = timeStamp;
        reactionList = new ArrayList<>();
    }
    public void addReaction(Reaction reaction){
        reactionList.add(reaction);
    }
}
