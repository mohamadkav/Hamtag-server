package net.hamtag.server.datatypes.ad;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.utils.Config;
import net.hamtag.server.utils.DateUtils;

public class AdShownMgr extends RootMgr{
/*	@SuppressWarnings("unchecked")
	public static List<AdShown> getAdsShownByAd(Ad ad){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.add(Restrictions.eq("ad", ad));
		return (List<AdShown>)criteria.list();
	}*/
/*	@SuppressWarnings("unchecked")
	public static List<AdShown> getAdsShownByDevice(Device device){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.add(Restrictions.eq("device", device));
		return (List<AdShown>)criteria.list();
	}*/
	@SuppressWarnings("unchecked")
	public static List<AdShown>getAdsShownByFilter(Date from,Date to,Date startPagesFromShownDate,int maxResults,
			Set<Corporation>corporations){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.setMaxResults(maxResults);
		criteria.addOrder(Order.desc("showDate"));
		if(from!=null)
			criteria.add(Restrictions.ge("showDate", from));
		if(to!=null)
			criteria.add(Restrictions.le("showDate", to));
		if(startPagesFromShownDate!=null)
			criteria.add(Restrictions.le("showDate", startPagesFromShownDate));
		criteria.createAlias("ad","adalias");
		criteria.add(Restrictions.in("adalias.corporation", corporations));
		return criteria.list();
	}
	public static Long getActiveDeviceCountByNews(){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,-1*Config.DAYS_THAT_DEVICE_CONSIDERS_ACTIVE);
		criteria.add(Restrictions.ge("showDate", calendar.getTime()));
		criteria.setProjection(Projections.countDistinct("device"));
		return (Long)criteria.uniqueResult();
	}
	
	public static AdShown getAdsShownByDeviceAndAd(Device device,Ad ad){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.add(Restrictions.eq("device", device));
		criteria.add(Restrictions.eq("ad", ad));
		criteria.addOrder(Order.desc("percentage"));
		criteria.setMaxResults(1);
		return (AdShown)criteria.uniqueResult();
	}
	public static Long getCountShownForDay(Date day){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.ge("showDate", DateUtils.getStartOfDay(day)));
		criteria.add(Restrictions.le("showDate", DateUtils.getEndOfDay(day)));
		return (Long)criteria.uniqueResult();
	}
}
