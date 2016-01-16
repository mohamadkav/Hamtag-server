package net.hamtag.server.api.request.types.device;

import net.hamtag.server.api.response.Response;
import net.hamtag.server.api.response.types.device.TokenDTO;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.utils.GenerateTokenUtil;

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
		if(password.equals(device.getPassword())){
			device.setToken(GenerateTokenUtil.generateNewToken());
			DeviceMgr.update(device);
			TokenDTO dto=new TokenDTO();
			dto.setToken(device.getToken());
			return new Response(dto);
		}
		return new Response(Error.WRONG_PASSWORD);
	}
}
