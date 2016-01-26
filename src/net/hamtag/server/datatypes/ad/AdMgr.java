package net.hamtag.server.datatypes.ad;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.utils.Config;

public class AdMgr extends RootMgr {
	@SuppressWarnings("unchecked")
	public static List<Ad> getAdsByCorporation(Corporation c){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("corporation", c));
		return (List<Ad>)criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<Ad>getAdsByDate(String lastUpdateTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		if(lastUpdateTime!=null&&!(lastUpdateTime.trim().isEmpty()))
			criteria.add(Restrictions.ge("publishTime", new Date(Long.parseLong(lastUpdateTime))));
		if(maxNumber==null||maxNumber.trim().isEmpty())
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else
			criteria.setMaxResults(Integer.parseInt(maxNumber));
		criteria.add(Restrictions.sqlRestriction(Config.DATABASE_RANDOM_QUERY));
		return criteria.list();
	}
}
