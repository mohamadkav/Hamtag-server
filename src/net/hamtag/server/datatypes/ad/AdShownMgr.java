package net.hamtag.server.datatypes.ad;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;
import net.hamtag.server.datatypes.device.Device;

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
}
