package com.qfedu.house.persistence;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO <E, K extends Serializable> {

	K save(E entity);
	
	boolean deleteById(K id);
	
	void delete(E entity);
	
	void upate(E entity);
	
	List<E> findAll();
	
	E findById(K id);
}
