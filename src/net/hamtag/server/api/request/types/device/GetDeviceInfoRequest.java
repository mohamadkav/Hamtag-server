package net.hamtag.server.api.request.types.device;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.DeviceInfoDTO;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class GetDeviceInfoRequest extends BaseDeviceRequest{
	public GetDeviceInfoRequest(String phoneNumber,String token){
		setPhoneNumber(phoneNumber);
		setToken(token);
	}
	
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		DeviceInfoDTO dto=new DeviceInfoDTO();
		dto.setCharge(device.getCharge());
		dto.setName(device.getName());
		return new HamtagResponse(dto).getResponse(null);
	}

	@Override
	@Deprecated
	public BaseRequestHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
