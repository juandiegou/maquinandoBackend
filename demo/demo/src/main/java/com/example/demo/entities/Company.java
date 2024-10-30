package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany()
    private List<User> employes;

    public Company(){}

    public Company(String name){
        this.name = name;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<User> getEmployes() {
        return employes;
    }

    public void setEmployes(List<User> employes) {
        this.employes = employes;
    }

    public void addEmployee(User user) {
        this.employes.add(user);
        user.getCompanies().add(this);
    }

    public void removeEmployee(User user) {
        this.employes.remove(user);
        user.getCompanies().remove(this);
    }

}
