package org.clt.repository.dao;

import java.io.Serializable;
import java.util.Set;

import org.springframework.stereotype.Repository;

//@Repository
public interface CustomFieldsSingleObjectDao<E extends Serializable, PK extends Serializable> {
	
	E findEnableFieldsById(Set<String> fields, String id);
}
