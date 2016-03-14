package net.hamtag.server.datatypes.ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.utils.Config;

public class AdMgr extends RootMgr {
/*	@SuppressWarnings("unchecked")
	public static List<Ad> getAdsByCorporation(Corporation c){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("corporation", c));
		criteria.add(Restrictions.eq("isRelatedToNews", false));
		return (List<Ad>)criteria.list();
	}
*/	@SuppressWarnings("unchecked")
	public static List<Ad>getAdsByDate(String lastUpdateTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		if(lastUpdateTime!=null&&!(lastUpdateTime.trim().isEmpty()))
			criteria.add(Restrictions.ge("publishTime", new Date(Long.parseLong(lastUpdateTime))));
		if(maxNumber==null||maxNumber.trim().isEmpty())
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else
			criteria.setMaxResults(Integer.parseInt(maxNumber));
		criteria.add(Restrictions.eq("isRelatedToNews", false));
		criteria.add(Restrictions.sqlRestriction(Config.DATABASE_RANDOM_QUERY));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<Ad>getAdsByPublishTime(int maxResults,Date lastPublishTime,Set<Corporation> corporations,boolean canSeeAll){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		if(maxResults>Config.DEFAULT_MAX_RESULTS)
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else
			criteria.setMaxResults(maxResults);
		criteria.add(Restrictions.le("publishTime", lastPublishTime));
		if(corporations!=null&&!corporations.isEmpty()){
			criteria.add(Restrictions.in("corporation", corporations));
		}
		if((corporations==null||corporations.isEmpty())&&!canSeeAll)
			return new ArrayList<Ad>();
		criteria.addOrder(Order.desc("publishTime"));
		return criteria.list();
	}
	public static Long getAllAdsCount(){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public static List<Ad>list(){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		return criteria.list();
	}
	public static Long getCountForUser(User user){
		Criteria criteria=getInstance().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
}
