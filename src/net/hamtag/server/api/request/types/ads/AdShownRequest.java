package net.hamtag.server.api.request.types.ads;

import java.util.Date;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.ad.AdShown;
import net.hamtag.server.datatypes.ad.AdShownMgr;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;
import net.hamtag.server.utils.DateValidator;

public class AdShownRequest extends BaseDeviceRequest{
	private enum Error{
		DEVICE_ALREADY_HAS_SHOWN,
		AD_NOT_FOUND,
		DATE_INVALID,
		SHOWN_SECONDS_INVALID
	}
	private String adId,shownDate,shownSeconds;
	public AdShownRequest(String adId, String token,String phoneNumber,String shownDate,String shownSeconds){
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
		Ad ad=AdMgr.getInstance().get(Ad.class,Long.parseLong(adId));
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		if(ad==null)
			return new HamtagResponse(Error.AD_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		AdShown adShown=new AdShown();
		adShown.setAd(ad);
		adShown.setDevice(device);
		if(!DateValidator.validateLongStringDate(shownDate))
			return new HamtagResponse(Error.DATE_INVALID).getResponse(Response.Status.BAD_REQUEST);
		adShown.setShowDate(new Date(Long.parseLong(shownDate)));
		try{
			adShown.setShownTime(Integer.parseInt(shownSeconds));
		}catch(NumberFormatException e){
			return new HamtagResponse(Error.SHOWN_SECONDS_INVALID).getResponse(Response.Status.BAD_REQUEST);
		}
		AdShownMgr.add(adShown);
		device.setCharge(device.getCharge()+ad.getPrice());
		DeviceMgr.update(device);
		return new HamtagResponse().getResponse(null);
	}
}
