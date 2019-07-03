package com.CODEns.BackendAPI.Controllers;

import com.CODEns.BackendAPI.DTOs.UserDTO;
import com.CODEns.BackendAPI.Entities.User;
import com.CODEns.BackendAPI.Interfaces.ControllerInterface;
import com.CODEns.BackendAPI.Security.JwtRequest;
import com.CODEns.BackendAPI.Security.JwtResponse;
import com.CODEns.BackendAPI.Security.JwtTokenUtil;
import com.CODEns.BackendAPI.Services.JwtUserDetailsService;
import com.CODEns.BackendAPI.Services.UsersService;
import com.CODEns.BackendAPI.Utils.GenericResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/users")
public class UsersController implements ControllerInterface<UserDTO, User> {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UsersService users_service;
	
	@Autowired
	private GenericResourceAssembler<UserDTO> resource_assembler;

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> add(@RequestBody User entity) {
		System.out.println(entity.getEmail());
		entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(16)));
		UserDTO user_dto = users_service.save(entity);
		return resource_assembler.toResource(user_dto);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<UserDTO>> getAll() {
		// regresa a json todos los usuarios
    	List<Resource<UserDTO>> users_dto = users_service.findAll().stream()
    			.map(user -> resource_assembler.toResource(user)).collect(Collectors.toList());
    	return new Resources<>(users_dto);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> getById(@PathVariable Integer id) {
		UserDTO user_dto = users_service.getById(id);
		return resource_assembler.toResource(user_dto);
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> deleteById(@PathVariable Integer id) {
		UserDTO user = users_service.deleteById(id);
		return resource_assembler.toResource(user);
	}

	
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<UserDTO> update(@RequestBody User entity, @PathVariable Integer id) {
		// se tuvo que dejar con la variable en el url debido a que ModelAttribute no mapeo el atributo id_usuario
		entity.setId(id);
		UserDTO user_dto = users_service.update(entity);
		return resource_assembler.toResource(user_dto);
	}
	
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7:00"));
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		UserDTO current = new UserDTO(this.users_service.findByUsername(userDetails.getUsername()));
		return ResponseEntity.ok(new JwtResponse(token, dateFormat.format(jwtTokenUtil.getExpirationDateFromToken(token)), current));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
