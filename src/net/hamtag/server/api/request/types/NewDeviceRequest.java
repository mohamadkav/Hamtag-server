package net.hamtag.server.api.request.types;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.SmsConfirmationUtil;

public class NewDeviceRequest {
	enum Error{
		NUMBER_ALREADY_HAS_VALID_TOKEN,
		NUMBER_NULL_OR_INVALID,
		NUMBER_ALREADY_ENROLLED,
		REQUEST_SENDING_ERROR;
	}
	public static Response addNewDevice(String number,boolean reallySendMessage){
		if(number==null){
			return new Response(Error.NUMBER_NULL_OR_INVALID);
		}
		if(DeviceMgr.getDeviceByPhoneNumber(number)!=null)
			return new Response(Error.NUMBER_ALREADY_ENROLLED);
		//DELETE THE INVALID DATA FIRST!
		long date=new Date().getTime();
		List<TempDevice> allDevices=TempDeviceMgr.list();
		Transaction tx = TempDeviceMgr.getInstance().beginTransaction();
		for(TempDevice t:allDevices){
			if(t.getValidUntill().getTime()<date){
				Object o=TempDeviceMgr.getInstance().load(TempDevice.class, t.getId());
				TempDeviceMgr.getInstance().delete(o);
			}
		}
		tx.commit();
		allDevices=TempDeviceMgr.list();
		for(TempDevice t:allDevices){
			if(t.getPhoneNumber().equals(number))
				return new Response(Error.NUMBER_ALREADY_HAS_VALID_TOKEN);
		}
		
		
		String token=SmsConfirmationUtil.generateToken();
		if(reallySendMessage){
			boolean success=SmsConfirmationUtil.sendMessage(number, token);
			if(!success)
				return new Response(Error.REQUEST_SENDING_ERROR);
		}
		TempDevice tempDevice=new TempDevice();
		tempDevice.setPhoneNumber(number);
		tempDevice.setToken(token);
		date=new Date().getTime();
		//3 Minutes Validation
		date+=180000;
		tempDevice.setValidUntill(new Date(date));
		TempDeviceMgr.add(tempDevice);
		return new Response();
	}
}