package net.hamtag.server.datatypes.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class UserMgr extends RootMgr{
	public static User getByUsername(String username){
		Criteria criteria=getInstance().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User)criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public static List<User>list(){
		Criteria criteria=getInstance().createCriteria(User.class);
		return criteria.list();
	}
}
