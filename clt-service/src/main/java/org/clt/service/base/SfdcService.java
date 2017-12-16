package org.clt.service.base;

import org.clt.repository.pojo.Sfdc;
import org.springframework.stereotype.Service;

@Service
public interface SfdcService extends GenericService<Sfdc, String> {
	
	//List<Sfdc> findAllByContactId(String conId);
}
