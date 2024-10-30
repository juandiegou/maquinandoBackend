package com.example.demo.controllers;
import com.example.demo.repositories.PositionRepository;
import com.example.demo.entities.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;





@RestController
@RequestMapping("/positions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/")
    public List<Position> getAllPositions() {
        return this.positionRepository.findAll();
    }
    


    @GetMapping("/{id}")
    public Position getPosition(@PathVariable Long id) {
        return this.positionRepository.findById(id).orElse(null);
    }
   
    @PostMapping("/")
    public Position createPosition(@RequestBody @Validated Position position) {
        return this.positionRepository.save(position);
    }

    @PutMapping("/{id}")
    public Position updateAllPosition(@PathVariable Long id, @RequestBody Position position) {
        return positionRepository.saveAndFlush(position);
    }
    
        
    @PatchMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position positionRequest) {
        Position currentPosition = this.positionRepository.findById(id).orElse(null);

        if (currentPosition != null) {
            BeanUtils.copyProperties(positionRequest, currentPosition, getNullPropertyNames(positionRequest));
            return this.positionRepository.save(currentPosition);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id){
        this.positionRepository.deleteById(id);
    }

    
     /**
     * Obtiene el nombre de las propiedades nulas de un objeto.
     *
     * @param source El objeto del que se desean obtener las propiedades nulas.
     * @return Un arreglo de cadenas que contiene el nombre de las propiedades nulas.
     */
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] propertyDescriptors = wrappedSource.getPropertyDescriptors();
        List<String> nullProperties = new ArrayList<>();
        for (java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (wrappedSource.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }
}
