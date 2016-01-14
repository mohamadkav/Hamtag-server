package net.hamtag.server.datatypes.ad;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.corporation.Corporation;

public class AdMgr extends RootMgr {
	@SuppressWarnings("unchecked")
	public static List<Ad> getAdsByCorporation(Corporation c){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("corporation", c));
		return (List<Ad>)criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<Ad>getAdsByCategory(List<String> categories,String lastUpdateTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		if(lastUpdateTime!=null&&!(lastUpdateTime.trim().isEmpty()))
			criteria.add(Restrictions.ge("publishTime", new Date(Long.parseLong(lastUpdateTime))));
		if(maxNumber==null||maxNumber.trim().isEmpty())
			//TODO: CONFIG
			criteria.setMaxResults(200);
		else
			criteria.setMaxResults(Integer.parseInt(maxNumber));
		if(categories!=null&&!categories.isEmpty()){
			criteria.createAlias("categories", "categoriesAlias");
			criteria.add(Restrictions.in("categoriesAlias.name", categories));
		}
		criteria.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		return criteria.list();
	}
}
