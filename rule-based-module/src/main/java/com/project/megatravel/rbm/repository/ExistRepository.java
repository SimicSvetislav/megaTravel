package com.project.megatravel.rbm.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

@Repository
public interface ExistRepository {
	
	public Object save(Object entity);
	public Object getOneById(Long id);
	public Collection<? extends Object> getAll();
	public Object deleteById(Long id);
	
}
