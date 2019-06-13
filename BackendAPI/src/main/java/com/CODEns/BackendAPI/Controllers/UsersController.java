package com.CODEns.BackendAPI.Controllers;

import com.CODEns.BackendAPI.Entities.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @RequestMapping("/users")
    public User getUser(@RequestParam(value = "nombre", defaultValue = "No Hay Usuario") String nombre) {
        return new User(nombre);
    }
}