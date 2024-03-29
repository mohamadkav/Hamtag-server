package net.hamtag.server.datatypes.device;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class TempDeviceMgr extends RootMgr{
	@SuppressWarnings("unchecked")
	public static List<TempDevice>list(){
		Criteria c=getInstance().createCriteria(TempDevice.class);
		return c.list();
	}
	public static TempDevice getByNumber(String number){
		Criteria criteria=getInstance().createCriteria(TempDevice.class);
		criteria.add(Restrictions.eq("phoneNumber", number));
		return (TempDevice)criteria.uniqueResult();
	}
}
