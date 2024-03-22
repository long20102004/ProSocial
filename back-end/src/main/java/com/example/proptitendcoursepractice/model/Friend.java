package com.example.proptitendcoursepractice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Friend {
    @Id
    private int id;
    private String name;
    private String avatar;
}
