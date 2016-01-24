package net.hamtag.server.api.request.types.ads;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class AdLikeRequest extends BaseDeviceRequest{
	private enum Error{
		DEVICE_ALREADY_LIKED
	}
	private Long adId;
	public AdLikeRequest(String id,String token,String phoneNumber){
		adId=Long.parseLong(id);
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		Ad ad=AdMgr.getInstance().get(Ad.class,adId);
		boolean success=ad.addToLikes(DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber()));
		if(!success)
			return new HamtagResponse(Error.DEVICE_ALREADY_LIKED).getResponse(Response.Status.BAD_REQUEST);
		AdMgr.update(ad);
		return new HamtagResponse().getResponse(null);
	}
	@Override
	public BaseRequestHandler getHandler() {
		return null;
	}
}
