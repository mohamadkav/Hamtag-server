package net.hamtag.server.datatypes.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class UserRoleMgr extends RootMgr{
	public static UserRole getByName(String name){
		Criteria criteria=getInstance().createCriteria(UserRole.class);
		criteria.add(Restrictions.eq("role", name));
		return (UserRole)criteria.uniqueResult();
	}
}
