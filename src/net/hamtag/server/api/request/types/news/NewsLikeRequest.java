package net.hamtag.server.api.request.types.news;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;

@SuppressWarnings("unused")
public class NewsLikeRequest extends BaseDeviceRequest{
	private enum Error{
		DEVICE_ALREADY_LIKED,
		NEWS_NOT_FOUND
	}
	private Long newsId;
	public NewsLikeRequest(String id,String token,String phoneNumber){
		newsId=Long.parseLong(id);
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		News news=NewsMgr.getInstance().get(News.class,newsId);
		if(news==null)
			return new HamtagResponse(Error.NEWS_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		boolean success=news.addToLikes(DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber()));
		if(!success)
			return new HamtagResponse(Error.DEVICE_ALREADY_LIKED).getResponse(Response.Status.BAD_REQUEST);
		NewsMgr.update(news);
		return new HamtagResponse().getResponse(null);
	}
}
