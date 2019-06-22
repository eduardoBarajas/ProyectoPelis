package com.CODEns.BackendAPI.Interfaces;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * En esta interfaz estan definidos los metodos indispensables de CRUD para los controladores.
 * 
 * Las anotaciones no se pasan hacia los controladores solo estan como referencia.
 * 
 * Los metodos reciben objectos genericos 'T' para los DTO, y 'S' para las entidades que se usaran en el repositorio. 
 */

public interface ControllerInterface<D, E> {

	public Resource<D> add(@ModelAttribute E entity);
  
	public Resources<Resource<D>> getAll();
	
	public Resource<D> getById(@PathVariable Integer id);
	
	public Resource<D> deleteById(@PathVariable Integer id);
	
	public Resource<D> update(@ModelAttribute E entity, @PathVariable Integer id);
	
}
