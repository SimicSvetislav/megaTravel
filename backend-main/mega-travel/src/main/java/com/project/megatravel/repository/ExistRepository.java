package com.project.megatravel.repository;

import java.util.Collection;

public interface ExistRepository {
	
	public Object save(Object entity);
	public Object getOneById(Long id);
	public Collection<? extends Object> getAll();
	public Object deleteById(Long id);
	
}
