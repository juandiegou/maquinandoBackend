package com.example.demo.controllers;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.entities.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin
public class CompanyController {
    
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/")
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Company getCompany(@RequestParam Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Company createCompany(@RequestBody Company company) {
        return this.companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public Company updateAllCompany(@PathVariable Long id, @RequestBody Company company) {
        return this.companyRepository.saveAndFlush(company);
    }

    @PatchMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company){
        Company currentCompany = this.companyRepository.findById(id).orElse(null);
        if(currentCompany != null){
            BeanUtils.copyProperties(company, currentCompany,getNullPropertyNames(company));
            return this.companyRepository.save(company);
        }

        return null;
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
