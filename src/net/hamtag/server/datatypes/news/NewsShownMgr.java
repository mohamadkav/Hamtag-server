package net.hamtag.server.datatypes.news;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.device.Device;

public class NewsShownMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<NewsShown>getNewsShownByDeviceAndDate(Date fromTimeToNow,Device device){
		Criteria criteria=getInstance().createCriteria(NewsShown.class);
		criteria.add(Restrictions.eq("device", device));
		criteria.add(Restrictions.ge("showDate", fromTimeToNow));
		return criteria.list();
	}
	public static NewsShown getNewsShownByDeviceAndAd(Device device,News news){
		Criteria criteria=getInstance().createCriteria(NewsShown.class);
		criteria.add(Restrictions.eq("device", device));
		criteria.add(Restrictions.eq("news", news));
		return (NewsShown)criteria.uniqueResult();
	}
}
