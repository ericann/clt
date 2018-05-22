package org.clt.service.impl.base;

import java.io.Serializable;
import java.util.List;

import org.clt.repository.dao.GenericDao;
import org.clt.service.base.GenericService;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<E extends Serializable, PK extends Serializable> implements GenericService<E, PK> {
	
	protected GenericDao<E, PK> genericDao;
	
	public GenericServiceImpl(GenericDao<E, PK> genericDao) {
		this.genericDao = genericDao;
	}
	
	public List<E> findAllByContactId(String conId) {
		return this.genericDao.findAllByContactId(conId);
	}
	
	public E findById(PK id) {
		return this.genericDao.findOne(id);
	}
	
	public List<E> findAll() {
		return this.genericDao.findAll();
	}
	
	public E save(E e) {
		return this.genericDao.save(e);
	}
	
}
