package net.hamtag.server.api.request.types.device;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class LoginDeviceRequest {
	private enum Error{
		DEVICE_NOT_IN_DB,
		WRONG_PASSWORD
	}
	private String number,password;
	public LoginDeviceRequest(String number,String password){
		this.number=number;
		this.password=password;
	}
	public Response handle(){
		Device device=DeviceMgr.getDeviceByPhoneNumber(number);
		if(device==null){
			 return new Response(Error.DEVICE_NOT_IN_DB);
		}
		if(password.equals(device.getPassword()))
			return new Response();
		return new Response(Error.WRONG_PASSWORD);
	}
}
