package net.hamtag.server.api.request.types.ads.android;

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
import net.hamtag.server.utils.Config;
import net.hamtag.server.utils.DateUtils;

public class AdShownRequest extends BaseDeviceRequest{
	private enum Error{
		DEVICE_ALREADY_HAS_SHOWN,
		AD_NOT_FOUND,
		DATE_INVALID,
		SHOWN_SECONDS_INVALID,
		INVALID_REQUEST
	}
	private String adId,shownDate,shownSeconds,percentage;
	public AdShownRequest(String adId, String token,String phoneNumber,String shownDate,String shownSeconds,String percentage){
		this.adId=adId;
		setToken(token);
		setPhoneNumber(phoneNumber);
		this.shownDate=shownDate;
		this.shownSeconds=shownSeconds;
		this.percentage=percentage;
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		Ad ad=AdMgr.getInstance().get(Ad.class,Long.parseLong(adId));
		Device device=DeviceMgr.getDeviceByPhoneNumber(getPhoneNumber());
		if(ad==null)
			return new HamtagResponse(Error.AD_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		double chargeToAdd=(double)ad.getPrice()*(Double.parseDouble(percentage)/100);
		if(chargeToAdd<0||chargeToAdd>Config.MAXIMUM_CHARGE||Double.parseDouble(percentage)>100)
			return new HamtagResponse(Error.INVALID_REQUEST).getResponse(Response.Status.BAD_REQUEST);
		if(!DateUtils.validateLongStringDate(shownDate))
			return new HamtagResponse(Error.DATE_INVALID).getResponse(Response.Status.BAD_REQUEST);
		try{
			Integer.parseInt(shownSeconds);
		}catch(NumberFormatException e){
			return new HamtagResponse(Error.SHOWN_SECONDS_INVALID).getResponse(Response.Status.BAD_REQUEST);
		}
		AdShown adShownFromPastIfExisted=AdShownMgr.getAdsShownByDeviceAndAd(device,ad);
		if(adShownFromPastIfExisted!=null){
			AdShown adShown=new AdShown();
			adShown.setDevice(device);
			adShown.setPercentage(Integer.parseInt(percentage));
			adShown.setShowDate(new Date(Long.parseLong(shownDate)));
			adShown.setShownTime(Integer.parseInt(shownSeconds));
			adShown.setAd(ad);
			AdShownMgr.add(adShown);
			if(adShownFromPastIfExisted.getPercentage()>Integer.parseInt(percentage))
				return new HamtagResponse(0).getResponse(null);
			int percentageDiff=Integer.parseInt(percentage)-adShownFromPastIfExisted.getPercentage();
			double fromOne=(double)(percentageDiff)/100;
			chargeToAdd=(double)ad.getPrice()*fromOne;
			device.setCharge(device.getCharge()+(int)Math.floor(chargeToAdd));
			DeviceMgr.update(device);
			return new HamtagResponse((int)Math.floor(chargeToAdd)).getResponse(null);
		}
		
		
		AdShown adShown=new AdShown();
		adShown.setAd(ad);
		adShown.setDevice(device);
		adShown.setShowDate(new Date(Long.parseLong(shownDate)));
		adShown.setShownTime(Integer.parseInt(shownSeconds));
		adShown.setPercentage(Integer.parseInt(percentage));
		AdShownMgr.add(adShown);
		device.setCharge(device.getCharge()+(int)Math.floor(chargeToAdd));
		DeviceMgr.update(device);
		return new HamtagResponse((int)Math.floor(chargeToAdd)).getResponse(null);
	}
}
