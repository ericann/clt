package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.BasicConfig;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface BasicConfigDao extends Repository<BasicConfig, String> {
	
	public BasicConfig save(BasicConfig bc);
	
	@Query("SELECT bc FROM BasicConfig bc WHERE bc.wechatAccount=:wechatAccount")
	public BasicConfig findByWechatAccount(@Param("wechatAccount") String wechatAccount);
	
	@Query("SELECT bc.wechatToken FROM BasicConfig bc")
	public List<String> findWechatToken();
	
	@Query("SELECT wechatAccessToken FROM BasicConfig WHERE wechatAccount=:wechatAccount")
	public String findWechatAccessTokenByWechatAccount(@Param("wechatAccount") String wechatAccount);
	
	@Query("SELECT bc FROM BasicConfig bc WHERE bc.id=:id")
	public BasicConfig findWechatAppIdAndWechatAppSecretById(@Param("id") String id);
	
	@Modifying
	@Query("UPDATE BasicConfig bc SET bc.wechatAccessToken=:wechatAccessToken")
	public Integer updateByWechatAccessToken(@Param("wechatAccessToken") String wechatAccessToken);
	
	@Modifying
	@Query("UPDATE BasicConfig bc SET bc.wechatAccessToken=:wechatAccessToken WHERE wechatAccount=:wechatAccount")
	public Integer updateWechatAccessTokenByWechatAccount(@Param("wechatAccount") String wechatAccount, @Param("wechatAccessToken") String wechatAccessToken);

	@Modifying
	@Query("UPDATE BasicConfig bc SET bc.wechatAccessToken=:wechatAccessToken WHERE id=:id")
	public Integer updateWechatAccessTokenById(@Param("wechatAccessToken") String wechatAccessToken, @Param("id") String id);
	
	@Query("SELECT bc FROM BasicConfig bc")
	public List<BasicConfig> findAll();
	
	@Query("SELECT bc FROM BasicConfig bc WHERE bc.accountId=:accId")
	public BasicConfig findByAccountId(@Param("accId") String accId);
}
