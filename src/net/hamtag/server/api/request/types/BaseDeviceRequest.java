package net.hamtag.server.api.request.types;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;


public abstract class BaseDeviceRequest extends BaseRequest{
	enum Error{
		AUTH_FAILED,
		TOKEN_NULL_OR_EMPTY,
		PHONE_NUMBER_NULL_OR_EMPTY,
		PHONE_NUMBER_INVALID
	}
	private String token,phoneNumber;
	public Response auth(){
		if(phoneNumber==null||phoneNumber.trim().isEmpty())
			return new Response(Error.PHONE_NUMBER_NULL_OR_EMPTY);
		if(token==null||token.trim().isEmpty())
			return new Response(Error.TOKEN_NULL_OR_EMPTY);
		Device device=DeviceMgr.getDeviceByPhoneNumber(phoneNumber);
		if(device==null)
			return new Response(Error.PHONE_NUMBER_INVALID);
		if(!token.equals(device.getToken()))
			return new Response(Error.AUTH_FAILED);
		return null;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
