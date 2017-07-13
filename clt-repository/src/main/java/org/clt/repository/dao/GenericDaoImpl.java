package org.clt.repository.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericDaoImpl<T extends Serializable, PK extends Serializable> implements GenericDao<T, PK> {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenericDao dao = ctx.getBean(GenericDao.class);
		System.out.println("-- dao: " + dao);
	}
	
	@PersistenceContext 
	private EntityManager em;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
             .getActualTypeArguments()[0];
    }
	
	@Override
	public T findById(PK id) {
		return this.em.find(entityClass, id);
	}

	@Override
	public Integer delete(PK id) {
		T t = this.findById(id);
		this.em.remove(t);
		return 0;
	}

	@Override
	public T save(T t) {
		this.em.persist(t);
		return t;
	}

	@Override
	public List<T> findByFields(Map<Object, Object> m) {
		for(Object o : m.keySet()) {
			
		}
		return null;
	}

	@Override
	public List<T> findByField(String key, String value) {
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append(entityClass).append(" WHERE ").append(key).append("=").append(value);
		Query query =  em.createNativeQuery(sql.toString(), entityClass);   
		return query.getResultList();
	}
	
}
