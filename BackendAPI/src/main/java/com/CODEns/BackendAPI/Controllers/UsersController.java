package com.CODEns.BackendAPI.Controllers;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users")
public class UsersController {
	
	@Autowired
	private UsersService users_service;
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<UserDTO> addNewUser(@ModelAttribute User user) {
    	UserDTO user_dto = users_service.saveUser(user);
    	return new Resource<>(user_dto,
    			linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Usuarios"));
    }
    
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value = "/{id_user}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> deleteUserById(@PathVariable Integer id_user) {
		UserDTO user = users_service.deleteUserById(id_user);
		return new Resource<>(user,
    			linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Usuarios"));
	}
	
	@RequestMapping(value = "/{id_user}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> updateUser(@ModelAttribute User user, @PathVariable Integer id_user) {
    	// se tuvo que dejar con la variable en el url debido a que ModelAttribute no mapeo el atributo id_usuario
		user.setId(id_user);
		UserDTO user_dto = users_service.updateUser(user);
    	return new Resource<>(user_dto,
    			linkTo(methodOn(UsersController.class).getAllUsers()).withRel("Usuarios"));
    }
	
	
    
}