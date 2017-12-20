package org.clt.repository.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

//@NoRepositoryBean
//@Repository
public class CustomFieldsSingleObjectDaoImpl<E extends Serializable, PK extends Serializable>
	extends SimpleJpaRepository<E, PK> implements CustomFieldsSingleObjectDao<E, PK>{
	
	private final EntityManager entityManager;
	private String entityName;

	public CustomFieldsSingleObjectDaoImpl(Class<E> entityClass, EntityManager entityManager) {
	    super(entityClass, entityManager);
	    this.entityManager = entityManager;
	    this.entityName = entityClass.getName();
	}
	
	public CustomFieldsSingleObjectDaoImpl(JpaEntityInformation<E, PK> entityInformation, EntityManager entityManager) {   
	    super(entityInformation, entityManager);   
	    this.entityManager = entityManager;   
	    this.entityName = entityInformation.getEntityName();
	}   
	
	@SuppressWarnings("unchecked")
	@Override
	public E findEnableFieldsById(Set<String> fields, String id) {
		
		StringBuilder sql = new StringBuilder("SELECT id");
		for(String field : fields) {
			sql.append("," + field);
		}
		sql.append(" FROM " + this.entityName);
		sql.append(" WHERE id=" + id);
		
		System.out.println("-- : " + sql.toString());
		
		return (E)entityManager.createQuery(sql.toString()).getSingleResult();
	}
	
	public List<E> findEnableFields(Set<String> fields) {
		return null;
	}
}
