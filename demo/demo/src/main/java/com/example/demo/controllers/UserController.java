package com.example.demo.controllers;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
      // Repositorio de usuarios que maneja la persistencia de los datos.
      @Autowired
      private UserRepository userRepository;
  
      /**
       * Obtiene todos los usuarios almacenados en la base de datos.
       *
       * @return Una lista de todos los usuarios en la base de datos.
       */
         /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de todos los usuarios en la base de datos.
     */
    @GetMapping("/")
    public List<User> getAllUsuarios() {
        return this.userRepository.findAll();
    }

    /**
     * Obtiene un usuario específico por su identificador (ID).
     *
     * @param id El identificador único del usuario a obtener.
     * @return El usuario con el ID especificado o null si no se encuentra en la base de datos.
     */
    @GetMapping("/{id}")
    public User getUsuarioById(@PathVariable Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo usuario y lo guarda en la base de datos.
     *
     * @param usuario El objeto Usuarios que contiene la información del nuevo usuario.
     * @return El usuario creado y almacenado en la base de datos.
     */
    @PostMapping("/")
    public User createUsuario(@RequestBody @Validated User usuario) {
        return userRepository.save(usuario);
    }

    /**
     * Actualiza todos los atributos de un usuario existente en la base de datos.
     *
     * @param id      El identificador único del usuario a actualizar.
     * @param usuario El objeto Usuarios que contiene los nuevos datos para el usuario.
     * @return El usuario actualizado y almacenado en la base de datos.
     */
    @PutMapping("/{id}")
    public User updateAllUsuario(@PathVariable Long id, @RequestBody @Validated User usuario) {
        usuario.setId(id);
        return userRepository.save(usuario);
    }

    /**
     * Actualiza solo los atributos no nulos de un usuario existente en la base de datos.
     *
     * @param id             El identificador único del usuario a actualizar.
     * @param usuarioRequest El objeto Usuarios que contiene los nuevos datos para el usuario.
     * @return El usuario actualizado y almacenado en la base de datos.
     */
    @PatchMapping("/{id}")
    public User updateUsuario(@PathVariable Long id, @RequestBody User usuarioRequest) {
        User usuarioActual = userRepository.findById(id).orElse(null);

        if (usuarioActual != null) {
            // Copia solo los atributos no nulos del usuarioRequest al usuarioActual.
            BeanUtils.copyProperties(usuarioRequest, usuarioActual, getNullPropertyNames(usuarioRequest));
            return this.userRepository.save(usuarioActual);
        }

        return null;
    }

    /**
     * Elimina un usuario de la base de datos por su identificador (ID).
     *
     * @param id El identificador único del usuario a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        this.userRepository.deleteById(id);
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
