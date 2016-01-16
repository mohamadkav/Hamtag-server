package net.hamtag.server.api.request.types.device;

import java.util.Date;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.api.response.types.device.TokenDTO;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.TempDevice;
import net.hamtag.server.datatypes.device.TempDeviceMgr;
import net.hamtag.server.utils.GenerateTokenUtil;

public class ConfirmDeviceRequest {
	private enum Error{
		TOKEN_INVALID,
		NUMBER_NOT_IN_TEMP_DEVICES,
		NUMBER_ALREADY_ENROLLED
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
		if(DeviceMgr.getDeviceByPhoneNumber(number)!=null)
			return new Response(Error.NUMBER_ALREADY_ENROLLED);
		if(tempDevice.getToken().equals(token)&&tempDevice.getValidUntill().getTime()>=new Date().getTime()){
			Device device = new Device();
			device.setPhoneNumber(number);
			device.setPassword(tempDevice.getPassword());
			device.setToken(GenerateTokenUtil.generateNewToken());
			DeviceMgr.add(device);
			TokenDTO dto=new TokenDTO();
			dto.setToken(device.getToken());
			return new Response(dto);
		}
		return new Response(Error.TOKEN_INVALID);
	}
}
