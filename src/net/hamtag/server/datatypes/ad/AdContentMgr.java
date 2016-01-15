package net.hamtag.server.datatypes.ad;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class AdContentMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<Integer>getContentIdsByAd(Ad ad){
		Criteria criteria=getInstance().createCriteria(AdContent.class);
		criteria.add(Restrictions.eq("ad", ad));
		criteria.setProjection(Projections.property("id"));
		return criteria.list();
	}
}
