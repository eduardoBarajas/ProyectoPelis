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

public interface ControllerInterface<T, S> {

	public Resource<T> add(@ModelAttribute S entity);
  
	public Resources<Resource<T>> getAll();
	
	public Resource<T> getById(@PathVariable Integer id);
	
	public Resource<T> deleteById(@PathVariable Integer id);
	
	public Resource<T> update(@ModelAttribute S entity, @PathVariable Integer id);
	
}
