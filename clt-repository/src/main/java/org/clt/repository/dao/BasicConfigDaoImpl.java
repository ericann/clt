package org.clt.repository.dao;

public class BasicConfigDaoImpl {}
/*@Repository("basicConfigDao")
public class BasicConfigDaoImpl implements BasicConfigDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public BasicConfig save(BasicConfig bc) {
		try {
			em.persist(bc);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return bc;
	}

	@Override
	public BasicConfig findByWechatAccount(String wechatAccount) {
		BasicConfig bc = null;
		try {
			bc = (BasicConfig) em.createNativeQuery("SELECT * FROM BasicConfig WHERE wechatAccount=\"" + wechatAccount + "\"", BasicConfig.class).getSingleResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bc;
	}

	@Override
	public List<BasicConfig> findWechatTokenByWechatAccount(String wechatAccount) {
		
		List<BasicConfig> bcList = em.createNativeQuery("SELECT wechatToen FROM BasicConfig", BasicConfig.class).getResultList();
		return bcList;
	}
	
}
*/