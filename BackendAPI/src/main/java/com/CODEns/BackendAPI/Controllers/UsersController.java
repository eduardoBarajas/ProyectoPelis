package com.CODEns.BackendAPI.Controllers;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Interfaces.ControllerInterface;
import com.CODEns.BackendAPI.Services.UsersService;
import com.CODEns.BackendAPI.Utils.GenericResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/users")
public class UsersController implements ControllerInterface<UserDTO, User> {
	
	@Autowired
	private UsersService users_service;
	
	private GenericResourceAssembler<UserDTO> resource_assembler;
	
	@Autowired
	public UsersController(GenericResourceAssembler<UserDTO> resource) {
		this.resource_assembler = resource;
		this.resource_assembler.setController(this);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> add(@ModelAttribute User entity) {
		UserDTO user_dto = users_service.saveUser(entity);
		return resource_assembler.toResource(user_dto);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<UserDTO>> getAll() {
		// regresa a json todos los usuarios
    	List<Resource<UserDTO>> users_dto = users_service.findAllUsers().stream()
    			.map(user -> resource_assembler.toResource(user)).collect(Collectors.toList());
    	return new Resources<>(users_dto);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> getById(@PathVariable Integer id) {
		UserDTO user_dto = users_service.getUserById(id);
		return resource_assembler.toResource(user_dto);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> deleteById(@PathVariable Integer id) {
		UserDTO user = users_service.deleteUserById(id);
		return resource_assembler.toResource(user);
	}

	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> update(@ModelAttribute User entity, @PathVariable Integer id) {
		// se tuvo que dejar con la variable en el url debido a que ModelAttribute no mapeo el atributo id_usuario
		entity.setId(id);
		UserDTO user_dto = users_service.updateUser(entity);
		return resource_assembler.toResource(user_dto);
	}
    
}