package org.clt.repository.dao;

import org.clt.repository.pojo.Button;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface ButtonDao extends Repository<Button, String> {
	
	@Query("SELECT b FROM Button b WHERE b.isDefault=:isDefault AND b.basicConfig.id=:basicConfigId")
	public Button findByIsDefaultAndBasicConfigId(@Param("isDefault") Boolean isDefault, @Param("basicConfigId") String basicConfigId);
	
	@Query("SELECT b FROM Button b WHERE b.buttonId=:buttonId")
	public Button findByButtonId(@Param("buttonId") String buttonId);
	
	public Button save(Button b);
	
}
