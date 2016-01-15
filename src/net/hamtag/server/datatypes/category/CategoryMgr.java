package net.hamtag.server.datatypes.category;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class CategoryMgr extends RootMgr{
	public static Category getCategoryByName(String name){
		Criteria criteria=getInstance().createCriteria(Category.class);
		criteria.add(Restrictions.eq("name", name));
		return (Category)criteria.uniqueResult();
	}
}
