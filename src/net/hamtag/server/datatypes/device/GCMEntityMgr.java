package net.hamtag.server.datatypes.device;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class GCMEntityMgr extends RootMgr{
	public static GCMEntity getGCMEntityByToken(String token){
		Criteria criteria=getInstance().createCriteria(GCMEntity.class);
		criteria.add(Restrictions.eq("token", token));
		return (GCMEntity) criteria.uniqueResult();
	}
}
