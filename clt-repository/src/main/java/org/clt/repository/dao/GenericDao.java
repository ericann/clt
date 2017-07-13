package org.clt.repository.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.Repository;

public interface GenericDao<T extends Serializable, PK extends Serializable> extends Repository<T, PK> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	T findById(PK id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Integer delete(PK id);
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	T save(T t);
	
	/**
	 * 
	 * @param m
	 * @return
	 */
	List<T> findByFields(Map<Object, Object> m);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	List<T> findByField(String key, String value);

}
