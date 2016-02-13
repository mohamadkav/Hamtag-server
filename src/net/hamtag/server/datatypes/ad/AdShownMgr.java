package net.hamtag.server.datatypes.ad;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.utils.Config;

public class AdShownMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<AdShown> getAdsShownByAd(Ad ad){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.add(Restrictions.eq("ad", ad));
		return (List<AdShown>)criteria.list();
	}
	@SuppressWarnings("unchecked")
	public static List<AdShown> getAdsShownByDevice(Device device){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		criteria.add(Restrictions.eq("device", device));
		return (List<AdShown>)criteria.list();
	}
	public static Long getActiveDeviceCountByNews(){
		Criteria criteria=getInstance().createCriteria(AdShown.class);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,-1*Config.DAYS_THAT_DEVICE_CONSIDERS_ACTIVE);
		criteria.add(Restrictions.ge("showDate", calendar.getTime()));
		criteria.setProjection(Projections.countDistinct("device"));
		return (Long)criteria.uniqueResult();
	}
}
