package net.hamtag.server.datatypes.news;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.utils.Config;

public class NewsMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<News>getNewsByDate(String lastUpdateTime,String maxNumber){
		Criteria criteria=getInstance().createCriteria(News.class);
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
