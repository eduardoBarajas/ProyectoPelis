package com.CODEns.BackendAPI.Interfaces;

import java.util.List;

public interface ServiceInterface<E, D> {

	public D save(E entity);

	public List<D> findAll();
	
	public D getById(Integer id);
	
	public D deleteById(Integer id);
	
	public D update(E entity);
}
