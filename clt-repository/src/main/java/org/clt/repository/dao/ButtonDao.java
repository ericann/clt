package org.clt.repository.dao;

import org.clt.repository.pojo.Button;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ButtonDao extends GenericDao<Button, String> {
	
	@Query("SELECT b FROM Button b WHERE b.isDefault=:isDefault AND b.liveagent.id=:laId")
	public Button findByIsDefaultAndLaId(@Param("isDefault") Boolean isDefault, @Param("laId") String laId);
	
	@Query("SELECT b FROM Button b WHERE b.buttonId=:buttonId")
	public Button findByButtonId(@Param("buttonId") String buttonId);
	
}
