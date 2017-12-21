package org.clt.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public interface GenericService<E extends Serializable, PK extends Serializable> {
	
	E findById(String id);
	
	List<E> findAll();
	
	E save(E e);
	
	List<E> findAllByContactId(String conId);
	
}
