package com.CODEns.BackendAPI.Utils;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/*
 * Esta clase tiene como funcion la de crear la respuesta con los links hacia otro recursos como 
 * se indica segun los hypermedia-driven outputs.
 * 
 * Es generica por lo que funcionara con todos los DTOs definidos.
 * 
 */
@Component
public class GenericResourceAssembler<T> implements ResourceAssembler<T, Resource<T>>{
	
	@Override
	public Resource<T> toResource(T dto) {
		return  new Resource<>(dto);
	}
}
