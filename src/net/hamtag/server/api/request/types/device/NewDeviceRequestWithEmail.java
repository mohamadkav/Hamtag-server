package net.hamtag.server.api.request.types.device;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.Config;
import net.hamtag.server.utils.MailSenderHelper;
import net.hamtag.server.utils.SmsConfirmationUtil;

public class NewDeviceRequestWithEmail {
	private enum Error{
		EMAIL_ALREADY_HAS_VALID_TOKEN,
		EMAIL_NULL_OR_INVALID,
		EMAIL_ALREADY_ENROLLED,
		REQUEST_SENDING_ERROR;
	}
	private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
	          "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	          "\\@" +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	          "(" +
	          "\\." +
	          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	          ")+"
	      );
	private String email;
	private String password;
	public NewDeviceRequestWithEmail(String email,String password){
		this.email=email;
		this.password=password;
	}
	public Response handle(){
		if(email==null||email.trim().isEmpty()||!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
			return new HamtagResponse(Error.EMAIL_NULL_OR_INVALID).getResponse(Response.Status.BAD_REQUEST);
		}
		if(DeviceMgr.getDeviceByPhoneNumber(email)!=null)
			return new HamtagResponse(Error.EMAIL_ALREADY_ENROLLED).getResponse(Response.Status.UNAUTHORIZED);
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
			if(t.getPhoneNumber().equals(email))
				return new HamtagResponse(Error.EMAIL_ALREADY_HAS_VALID_TOKEN).getResponse(Response.Status.UNAUTHORIZED);
		}
		
		
		String token=SmsConfirmationUtil.generateToken();
		boolean success=MailSenderHelper.send(email, Config.fromEmail, Config.fromPassword, Config.emailSubject, token);
		if(!success)
			return new HamtagResponse(Error.REQUEST_SENDING_ERROR).getResponse(Response.Status.SERVICE_UNAVAILABLE);
		TempDevice tempDevice=new TempDevice();
		tempDevice.setPhoneNumber(email);
		tempDevice.setToken(token);
		tempDevice.setPassword(password);
		date=new Date().getTime();
		date+=Config.MESSAGE_VALIDATION_TIME;
		tempDevice.setValidUntill(new Date(date));
		TempDeviceMgr.add(tempDevice);
		return new HamtagResponse().getResponse(null);
	}
}
