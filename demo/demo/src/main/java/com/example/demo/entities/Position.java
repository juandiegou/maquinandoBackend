package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;

    @OneToMany(mappedBy = "position", cascade = CascadeType.REMOVE)
    private List<User> users;    

    // Default constructor required by JPA
    public Position() {}

    public Position(String position) {
        this.position = position;
    }

    public void setId(long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
