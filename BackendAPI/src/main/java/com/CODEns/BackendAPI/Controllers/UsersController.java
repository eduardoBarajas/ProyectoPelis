package com.CODEns.BackendAPI.Controllers;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Services.UsersService;
import com.CODEns.BackendAPI.Utils.ResponseField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users")
public class UsersController {
	
	@Autowired
	private UsersService users_service;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<UserDTO> addNewUser(@RequestParam(value = "nombre", defaultValue = "Nameless") String nombre) {
        User user = new User(nombre);
    	user.setCreationDate("14/06/2019");
    	user.setEmail(nombre+"@gmail.com");
    	user.setPrivateKey("d8dj8sd0fs-zsS");
    	user.setRole(1);
    	user.setUsername("Xx"+nombre.substring(0, 3)+"xX");
    	UserDTO user_dto = users_service.saveUser(user);
    	return new Resource<>(user_dto,
    			linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Usuarios"));
    }
    
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<UserDTO>> getAllUsers() {
    	// regresa a json todos los usuarios
    	List<Resource<UserDTO>> users_dto = users_service.findAllUsers().stream()
    			.map(user -> new Resource<>(user)).collect(Collectors.toList());
    	return new Resources<>(users_dto);
    }
	
	@RequestMapping(value = "/{id_user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> getUserById(@PathVariable Integer id_user) {
		UserDTO user_dto = users_service.getUserById(id_user);
		return new Resource<>(user_dto,
    			linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Usuarios"));
	}
    
}