package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Interfaces.ServiceInterface;
import com.CODEns.BackendAPI.Repositories.UserRepository;

/*
 * Este es el servicio del modulo de usuarios, es el que controla la logica de persistencia.
 */

@Service
public class UsersService implements ServiceInterface<User, UserDTO> {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO save(User entity) {
        UserDTO user_dto;
        if (userRepository.findByUsername(entity.getUsername()) != null) {
            System.out.println(entity.getUsername());
            user_dto = new UserDTO("Error", "Ya existe un usuario con ese nombre, por favor elige otro.");
            if (userRepository.findByEmail(entity.getEmail()) != null) {
                user_dto.setMessage(user_dto.getMessage() + "\n" + " y ese email tambien existe, elige otro.");
            }
        } else {
            if (userRepository.findByEmail(entity.getEmail()) == null) {
                User new_user = userRepository.save(entity);
                if (new_user != null) {
                    user_dto = new UserDTO(new_user, "Success", "Se creo el usuario con exito.");
                } else {
                    user_dto = new UserDTO(new_user, "Error", "Hubo un problema al crear el usuario, intenta mas tarde por favor.");
                }
            } else {
                user_dto = new UserDTO("Error", "Ya existe un usuario con ese email, por favor elige otro.");
            }
        }
		return user_dto;
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> users_dto = new LinkedList<>();
		for(User user: userRepository.findAll()) {
			users_dto.add(new UserDTO(user));
		}
		return users_dto;
	}

	@Override
	public UserDTO getById(Integer id) {
		UserDTO user_dto = new UserDTO("Error", "No se encontro el usuario en la base de datos.");
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).get();
			user_dto = new UserDTO(user, "Success", "Se obtuvo el usuario con exito.");
		}
		return user_dto;
	}

	@Override
	public UserDTO deleteById(Integer id) {
		UserDTO user_dto = new UserDTO("Error", "No existe ese usuario.");
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			user_dto.setMessage("Se elimino ese usuario con exito.");
			user_dto.setStatus("Success");
		}
		return user_dto;
	}

	@Override
	public UserDTO update(User entity) {
		UserDTO user_dto = new UserDTO("Error", "No se encontro el usuario en la base de datos.");
		if (userRepository.existsById(entity.getId())) {
			user_dto = new UserDTO(userRepository.save(entity), "Success", "Se actualizo el usuario con exito.");
		}
		return user_dto;
	}
}
