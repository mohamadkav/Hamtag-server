package net.hamtag.server.api.request.types.device.android;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.device.GCMEntity;
import net.hamtag.server.datatypes.device.GCMEntityMgr;

public class SetOrUpdateGCMTokenRequest extends BaseDeviceRequest{
	String gcmToken;
	private enum Error{
		GCM_TOKEN_NULL_OR_EMPTY
	}
	public SetOrUpdateGCMTokenRequest(String gcmToken,String token,String phoneNumber){
		setPhoneNumber(phoneNumber);
		setToken(token);
		this.gcmToken=gcmToken;
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		if(gcmToken==null||gcmToken.trim().isEmpty())
			return new HamtagResponse(Error.GCM_TOKEN_NULL_OR_EMPTY).getResponse(Response.Status.BAD_REQUEST);
		GCMEntity before=GCMEntityMgr.getGCMEntityByToken(gcmToken);
		if(before!=null){
			before.setDevice(DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber()));
			GCMEntityMgr.update(before);
			return new HamtagResponse().getResponse(null);
		}
		GCMEntity entity=new GCMEntity();
		entity.setDevice(DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber()));
		entity.setToken(gcmToken);
		GCMEntityMgr.add(entity);
		return new HamtagResponse().getResponse(null);
	}
}
