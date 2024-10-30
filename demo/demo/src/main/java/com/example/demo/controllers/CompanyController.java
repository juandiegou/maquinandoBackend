package com.example.demo.controllers;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.Company;
import com.example.demo.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
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

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.companyRepository.deleteById(id);
        }

        @PostMapping("/{companyId}/employes/{userId}")    
        public void addEmployeeToCompany(@PathVariable Long companyId, @PathVariable Long userId) {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Company not found"));
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            company.addEmployee(user);
            companyRepository.save(company);
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
