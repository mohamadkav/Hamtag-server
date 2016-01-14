package net.hamtag.server.api.request.types;

import java.util.Date;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;

public class ConfirmDeviceRequest {
	enum Error{
		TOKEN_INVALID,
		NUMBER_NOT_IN_TEMP_DEVICES
	}
	String number,token;
	public ConfirmDeviceRequest(String number, String token){
		this.number=number;
		this.token=token;
	}
	public Response handle(){
		TempDevice tempDevice=TempDeviceMgr.getByNumber(number);
		if(tempDevice==null)
			return new Response(Error.NUMBER_NOT_IN_TEMP_DEVICES);
		if(tempDevice.getToken().equals(token)&&tempDevice.getValidUntill().getTime()>=new Date().getTime()){
			Device device = new Device();
			device.setPhoneNumber(number);
			device.setPassword(tempDevice.getPassword());
			DeviceMgr.add(device);
			return new Response();
		}
		return new Response(Error.TOKEN_INVALID);
	}
}
