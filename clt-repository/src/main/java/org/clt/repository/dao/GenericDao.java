package org.clt.repository.dao;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<E extends Serializable, PK extends Serializable> 
	extends JpaRepository<E, PK>, JpaSpecificationExecutor<Object>{
	
	E findById(PK id);

	Object[] toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
