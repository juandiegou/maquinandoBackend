/**
 * Representa a un usuario en el sistema.
*/

package com.example.demo.entities;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;


@Entity
@Table(name = "users")
public class User {

    
    // Identificador único del usuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //Nombre 
    private String lastName; //apellido 
    private String dni; 
    private String cedula; 
    private String address; //dirección, 
    private BigDecimal salary; //salario, 
    @ManyToOne(cascade = CascadeType.ALL)
    private Position position; //cargo
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate = LocalDateTime.now(); //fecha de creación, 

    public User(){}

    public User( String name, String lastName, String dni, String 
        cedula, String address, BigDecimal salary, Position position){
            this.name = name;
            this.lastName = lastName;
            this.dni = dni;
            this.cedula = cedula;
            this.address = address;
            this.salary = salary;
            this.position = position;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
    
    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getDni(){
        return this.dni;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public String getCedula(){
        return this.cedula;
    }

    public void setCedula(String cedula){
        this.cedula = cedula;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public BigDecimal getSalary(){
        return this.salary;
    }

    public void setSalary(BigDecimal salary){
        this.salary = salary;
    }

    public String getPosition(){
        return this.position.getPosition();
    }

    public void setPosition(String position){
        this.position = new Position(position); 
    }
}
