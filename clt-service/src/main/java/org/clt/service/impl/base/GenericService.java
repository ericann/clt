package org.clt.service.impl.base;

import java.io.Serializable;
import java.util.List;

import org.clt.repository.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService<E extends Serializable, PK extends Serializable> {
	
	@Autowired
	protected GenericDao<E, PK> genericDao;
	
	E findById(PK id) {
		return null;
	}
	
	List<E> findAll() {
		return null;
	}
	
	E save(E e) {
		return null;
	}
}
