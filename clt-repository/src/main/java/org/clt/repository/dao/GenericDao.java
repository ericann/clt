package org.clt.repository.dao;

import java.io.Serializable;
import java.util.List;

import org.clt.repository.pojo.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
//@Repository
public interface GenericDao<E extends Serializable, PK extends Serializable> 
	extends JpaRepository<E, PK>, JpaSpecificationExecutor<Object>{
	
	@Query
	List<E> findAllByContactId(String conId);

	
}
