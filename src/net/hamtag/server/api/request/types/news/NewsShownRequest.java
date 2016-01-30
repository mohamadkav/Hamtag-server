package net.hamtag.server.api.request.types.news;

import java.util.Date;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.news.NewsMgr;
import net.hamtag.server.datatypes.news.NewsShown;
import net.hamtag.server.datatypes.news.NewsShownMgr;
import net.hamtag.server.utils.DateValidator;

public class NewsShownRequest extends BaseDeviceRequest{
	private enum Error{
		DEVICE_ALREADY_HAS_SHOWN,
		NEWS_NOT_FOUND,
		DATE_INVALID,
		SHOWN_SECONDS_INVALID
	}
	private String adId,shownDate,shownSeconds;
	public NewsShownRequest(String adId, String token,String phoneNumber,String shownDate,String shownSeconds){
		this.adId=adId;
		setToken(token);
		setPhoneNumber(phoneNumber);
		this.shownDate=shownDate;
		this.shownSeconds=shownSeconds;
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		News news=NewsMgr.getInstance().get(News.class,Long.parseLong(adId));
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		if(news==null)
			return new HamtagResponse(Error.NEWS_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		NewsShown newsShown=NewsShownMgr.getNewsShownByDeviceAndAd(DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber()), news);
		try{
			Integer.parseInt(shownSeconds);
		}catch(NumberFormatException e){
			return new HamtagResponse(Error.SHOWN_SECONDS_INVALID).getResponse(Response.Status.BAD_REQUEST);
		}
		if(newsShown!=null){
			newsShown.setShownTime(Integer.parseInt(shownSeconds)+newsShown.getShownTime());
			NewsShownMgr.update(newsShown);
			return new HamtagResponse().getResponse(null);
		}
		newsShown=new NewsShown();
		newsShown.setNews(news);
		newsShown.setDevice(device);
		if(!DateValidator.validateLongStringDate(shownDate))
			return new HamtagResponse(Error.DATE_INVALID).getResponse(Response.Status.BAD_REQUEST);
		newsShown.setShowDate(new Date(Long.parseLong(shownDate)));
		newsShown.setShownTime(Integer.parseInt(shownSeconds));
		NewsShownMgr.add(newsShown);
		return new HamtagResponse().getResponse(null);
	}
	@Override
	public BaseRequestHandler getHandler() {
		return null;
	}
}
