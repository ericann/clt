package org.clt.service.impl.base;

import java.io.Serializable;
import java.util.List;

import org.clt.repository.dao.GenericDao;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericService<E extends Serializable, PK extends Serializable> {
	
	protected GenericDao<E, PK> genericDao;
	
	public GenericService(GenericDao<E, PK> genericDao) {
		this.genericDao = genericDao;
	}

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
