package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Repositories.UserRepository;

/*
 * Este es el servicio del modulo de usuarios, es el que controla la logica de persistencia.
 */

@Service
public class UsersService {
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO saveUser(User user) {
		User new_user = userRepository.save(user);
		UserDTO user_dto;
		if (new_user.getId() > 0) {
			user_dto = new UserDTO(new_user, "Success", "Se almaceno el usuario con exito.");
		} else {
			user_dto = new UserDTO("Error", "No se pudo guardar el usuario en la base de datos.");
		}
		return user_dto;
	}
	
	public List<UserDTO> findAllUsers() {
		List<UserDTO> users_dto = new LinkedList<>();
		for(User user: userRepository.findAll()) {
			users_dto.add(new UserDTO(user));
		}
		return users_dto;
	}
	
	public UserDTO getUserById(Integer id) {
		UserDTO user_dto = new UserDTO("Error", "No se encontro el usuario en la base de datos.");
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).get();
			user_dto = new UserDTO(user, "Success", "Se obtuvo el usuario con exito.");
		}
		return user_dto;
	}
	
	public UserDTO deleteUserById(Integer id) {
		UserDTO user_dto = new UserDTO("Error", "No existe ese usuario.");
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			user_dto.setMessage("Se elimino ese usuario con exito.");
			user_dto.setStatus("Success");
		}
		return user_dto;
	}
	
	public UserDTO updateUser(User user) {
		UserDTO user_dto = new UserDTO("Error", "No se encontro el usuario en la base de datos.");
		if (userRepository.existsById(user.getId())) {
			user_dto = new UserDTO(userRepository.save(user), "Success", "Se actualizo el usuario con exito.");
		}
		return user_dto;
	}
}
