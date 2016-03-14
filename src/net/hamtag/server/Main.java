package net.hamtag.server;

import java.util.Date;
public class Main {
	public static void main(String[] args) {
		// TODO: change location parameter for ad to float


		System.out.println(new Date().getTime());
//		Query query=RootMgr.getInstance().createSQLQuery("select count(id) from ad_display ac where ac.adid in (select distinct id from ads ad where ad.id in(select adid from ad_category where categoryid=1))");
//		System.out.println(query.uniqueResult());
/*		List<ContentProvider>prov=ContentProviderMgr.list();
		Set <ContentProvider>toset=new HashSet<ContentProvider>(prov);
		for(User u:UserMgr.list()){
			u.setVisibleProviders(toset);
			UserMgr.update(u);
		}*/
/*		List<Corporation>prov=CorporationMgr.list();
		Set <Corporation>toset=new HashSet<Corporation>(prov);
		for(User u:UserMgr.list()){
			u.setVisibleCorporations(toset);
			UserMgr.update(u);
		}
		System.out.println(new Date().getTime());*/
	}
}
