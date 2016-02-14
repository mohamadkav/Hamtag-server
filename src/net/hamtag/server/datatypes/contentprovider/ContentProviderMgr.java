package net.hamtag.server.datatypes.contentprovider;

import java.util.List;

import org.hibernate.Criteria;

import net.hamtag.server.core.RootMgr;

public class ContentProviderMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<ContentProvider>list(){
		Criteria criteria=getInstance().createCriteria(ContentProvider.class);
		return criteria.list();
	}
}
