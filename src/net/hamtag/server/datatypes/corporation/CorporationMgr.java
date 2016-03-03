package net.hamtag.server.datatypes.corporation;

import java.util.List;

import org.hibernate.Criteria;

import net.hamtag.server.core.RootMgr;

public class CorporationMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<Corporation>list(){
		Criteria criteria=getInstance().createCriteria(Corporation.class);
		return criteria.list();
	}
}
