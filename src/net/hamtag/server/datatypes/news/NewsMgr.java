package net.hamtag.server.datatypes.news;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.utils.Config;

public class NewsMgr extends RootMgr{
	
	//TODO: This method is used in the android API. I will make this deprecated. you'll have to use the more precise method getNewsByPublishTime
	@SuppressWarnings("unchecked")
	public static List<News>getNewsByDate(String lastUpdateTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(News.class);
		if(lastUpdateTime!=null&&!(lastUpdateTime.trim().isEmpty()))
			criteria.add(Restrictions.ge("publishTime", new Date(Long.parseLong(lastUpdateTime))));
		if(maxNumber==null||maxNumber.trim().isEmpty())
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else{
			int max=Integer.parseInt(maxNumber);
			if(max>Config.MAXIMUM_RESULTS_VALIDITY)
				criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
			else
				criteria.setMaxResults(max);
		}
		criteria.addOrder(Order.desc("publishTime"));
	//	criteria.add(Restrictions.sqlRestriction(Config.DATABASE_RANDOM_QUERY));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<News>getNewsByContentProviderAndDate(ContentProvider contentProvider,String beforeTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(News.class);
		if(beforeTime!=null&&!(beforeTime.trim().isEmpty()))
			criteria.add(Restrictions.le("publishTime", new Date(Long.parseLong(beforeTime))));
		if(maxNumber==null||maxNumber.trim().isEmpty())
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else{
			int max=Integer.parseInt(maxNumber);
			if(max>Config.MAXIMUM_RESULTS_VALIDITY)
				criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
			else
				criteria.setMaxResults(max);
		}
		criteria.addOrder(Order.desc("publishTime"));
		criteria.add(Restrictions.eq("contentProvider", contentProvider));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<News>getNewsByPublishTime(Integer maxResults,Long beforeTime){
		Criteria criteria=getInstance().createCriteria(News.class);
		if(maxResults==null||maxResults>Config.DEFAULT_MAX_RESULTS)
			criteria.setMaxResults(Config.DEFAULT_MAX_RESULTS);
		else
			criteria.setMaxResults(maxResults);
		if(beforeTime!=null&&beforeTime>0){
			criteria.add(Restrictions.le("publishTime", new Date(beforeTime)));
		}
		criteria.addOrder(Order.desc("publishTime"));
		return criteria.list();
	}
}
