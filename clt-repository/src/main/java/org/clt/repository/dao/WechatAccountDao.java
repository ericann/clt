package org.clt.repository.dao;

import java.util.List;

import org.clt.repository.pojo.WechatAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WechatAccountDao extends GenericDao<WechatAccount, String> {
	
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.wechatAccount=:wechatAccount")
	public WechatAccount findByWechatAccount(@Param("wechatAccount") String wechatAccount);
	
	@Query("SELECT wa FROM WechatAccount wa JOIN wa.liveAgent la WHERE wa.liveAgent.id=la.id AND wa.wechatAccount=:wechatAccount")
	public WechatAccount findAndLiveAgentByWechatAccount(@Param("wechatAccount") String wechatAccount);
	
	@Query("SELECT wa.wechatToken FROM WechatAccount wa")
	public List<String> findWechatToken();
	
	@Query("SELECT wechatAccessToken FROM WechatAccount WHERE wechatAccount=:wechatAccount")
	public String findWechatAccessTokenByWechatAccount(@Param("wechatAccount") String wechatAccount);
	
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.id=:id")
	public WechatAccount findWechatAppIdAndWechatAppSecretById(@Param("id") String id);
	
	@Modifying
	@Query("UPDATE WechatAccount wa SET wa.wechatAccessToken=:wechatAccessToken WHERE wa.wechatAccount=:wechatAccount")
	public Integer updateWechatAccessTokenByWechatAccount(@Param("wechatAccount") String wechatAccount, @Param("wechatAccessToken") String wechatAccessToken);

	@Modifying
	@Query("UPDATE WechatAccount wa SET wa.wechatAccessToken=:wechatAccessToken WHERE wa.wechatAppId=:wechatAppId AND wa.wechatAppSecret=:wechatAppSecret")
	public Integer updateWechatAccessTokenByWechatAppIdAndWechatAppSecret(@Param("wechatAccessToken") String wechatAccessToken, @Param("wechatAppId") String wechatAppId, @Param("wechatAppSecret") String wechatAppSecret);

	@Modifying
	@Query("UPDATE WechatAccount wa SET wa.wechatAccessToken=:wechatAccessToken WHERE wa.id=:id")
	public Integer updateWechatAccessTokenById(@Param("wechatAccessToken") String wechatAccessToken, @Param("id") String id);
	
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.refreshByUs=:flag")
	public List<WechatAccount> findAllByRefreshByUs(Boolean flag);
	
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.account.id=:accId")
	public WechatAccount findByAccountId(@Param("accId") String accId);
	
	@Query("SELECT wa FROM WechatAccount wa JOIN wa.liveAgent la WHERE wa.liveAgent.id=la.id AND wa.id=:id")
	public WechatAccount findAndLiveAgentById(@Param("id") String id);
	
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.useDefault=:useDefault")
	public WechatAccount findByUseDefault(@Param("useDefault") Boolean useDefault);
	
	@Query("SELECT wa.wechatAccessToken FROM WechatAccount wa WHERE wa.useDefault=:useDefault")
	public String findWechatAccessTokenByUseDefault(@Param("useDefault") Boolean useDefault);
	
	@Override
	@Query("SELECT wa FROM WechatAccount wa WHERE wa.account.id=(SELECT con.account.id FROM Contact con WHERE con.id=:conId)")
	List<WechatAccount> findAllByContactId(@Param("conId") String conId);
}
