package net.hamtag.server.api.request.types.device;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.Config;
import net.hamtag.server.utils.SmsConfirmationUtil;

public class NewDeviceRequest {
	private enum Error{
		NUMBER_ALREADY_HAS_VALID_TOKEN,
		NUMBER_NULL_OR_INVALID,
		NUMBER_ALREADY_ENROLLED,
		REQUEST_SENDING_ERROR;
	}
	private String number;
	private boolean reallySendMessage;
	private String password;
	public NewDeviceRequest(String number,String password, boolean reallySendMessage){
		this.number=number;
		this.reallySendMessage=reallySendMessage;
		this.password=password;
	}
	public Response handle(){
		if(number==null){
			return new HamtagResponse(Error.NUMBER_NULL_OR_INVALID).getResponse(Response.Status.BAD_REQUEST);
		}
		if(DeviceMgr.getDeviceByPhoneNumber(number)!=null)
			return new HamtagResponse(Error.NUMBER_ALREADY_ENROLLED).getResponse(Response.Status.UNAUTHORIZED);
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
				return new HamtagResponse(Error.NUMBER_ALREADY_HAS_VALID_TOKEN).getResponse(Response.Status.UNAUTHORIZED);
		}
		
		
		String token=SmsConfirmationUtil.generateToken();
		if(reallySendMessage){
			boolean success=SmsConfirmationUtil.sendMessage(number, token);
			if(!success)
				return new HamtagResponse(Error.REQUEST_SENDING_ERROR).getResponse(Response.Status.SERVICE_UNAVAILABLE);
		}
		TempDevice tempDevice=new TempDevice();
		tempDevice.setPhoneNumber(number);
		tempDevice.setToken(token);
		tempDevice.setPassword(password);
		date=new Date().getTime();
		date+=Config.MESSAGE_VALIDATION_TIME;
		tempDevice.setValidUntill(new Date(date));
		TempDeviceMgr.add(tempDevice);
		return new HamtagResponse().getResponse(null);
	}
}
