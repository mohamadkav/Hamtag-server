package net.hamtag.server.datatypes.device;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.hamtag.server.core.RootMgr;

public class DeviceMgr extends RootMgr{
	public static Device getDeviceByPhoneNumber(String phoneNumber){
		Criteria criteria=getInstance().createCriteria(Device.class);
		criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
		return (Device) criteria.uniqueResult();
	}
	public static Long getDevicesCount(){
		Criteria criteria=getInstance().createCriteria(Device.class);
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	public static Long getTotalDebt(){
		Criteria criteria=getInstance().createCriteria(Device.class);
		criteria.setProjection((Projections.sum("charge")));
		return (Long)criteria.uniqueResult();
	}
}
