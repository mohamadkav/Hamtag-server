package net.hamtag.server.api.request.types.device.android;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class UpdateCredentialsRequest extends BaseDeviceRequest{
	private enum Error{
		NAME_NULL_OR_EMPTY
	}
	private String name;
	public UpdateCredentialsRequest(String name,String phoneNumber,String token){
		this.name=name;
		setPhoneNumber(phoneNumber);
		setToken(token);
	}
	@Override
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		if(name==null||name.trim().isEmpty())
			return new HamtagResponse(Error.NAME_NULL_OR_EMPTY).getResponse(Response.Status.BAD_REQUEST);
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		device.setName(name);
		DeviceMgr.update(device);
		return new HamtagResponse().getResponse(null);
	}
}
