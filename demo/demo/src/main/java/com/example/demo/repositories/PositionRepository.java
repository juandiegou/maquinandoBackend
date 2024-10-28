package com.example.demo.repositories;
import com.example.demo.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    
}
