package com.CODEns.BackendAPI.Utils;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import com.CODEns.BackendAPI.Interfaces.ControllerInterface;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * Esta clase tiene como funcion la de crear la respuesta con los links hacia otro recursos como 
 * se indica segun los hypermedia-driven outputs.
 * 
 * Es generica por lo que funcionara con todos los DTOs definidos.
 * 
 * Para crear los links es necesario saber los metodos de los controladores por lo que utiliza una interfaz
 * implementada en los controles usados.
 */
@Component
public class GenericResourceAssembler<T> implements ResourceAssembler<T, Resource<T>>{
	
	private ControllerInterface<?, ?> controller;
	
	@Override
	public Resource<T> toResource(T dto) {
		Integer id = 0;
		// En rel se almacena el nombre de referencia segun la entidad que entra
		String rel = dto.getClass().getName().split("\\.")[dto.getClass().getName().split("\\.").length - 1].replace("DTO", "");
		try {
			Method getIdMethod = dto.getClass().getMethod("getId");
			id = (Integer) getIdMethod.invoke(dto);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resource<T> resource = new Resource<>(dto, 
				linkTo(methodOn(controller.getClass()).getAll()).withRel(rel+"s"));
		// si el id es 0 no se agregara el link por que no existe el registro en la base de datos.
		if (id > 0) 
			resource.add(linkTo(methodOn(controller.getClass()).getById(id)).withRel(rel));
		return resource;
	}

	public void setController(ControllerInterface<?, ?> controller) {
		// El controlador es necesario insertarlo al momento de injectar la dependencia
		this.controller = controller;
	}
}
