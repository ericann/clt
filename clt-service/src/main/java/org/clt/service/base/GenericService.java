package org.clt.service.base;

import java.io.Serializable;
import java.util.List;

public interface GenericService<E extends Serializable, PK> {
	
	E findById(PK id);
	
	List<E> findAll();
	
	E save(E e);
}