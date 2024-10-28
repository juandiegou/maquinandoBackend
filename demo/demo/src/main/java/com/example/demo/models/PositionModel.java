package com.example.demo.models;

public class PositionModel {
    private Long id;
    private String position;


    public PositionModel(String position) {
        this.position = position;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    
    
} 