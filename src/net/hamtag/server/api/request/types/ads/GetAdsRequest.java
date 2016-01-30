package net.hamtag.server.api.request.types.ads;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.handler.ads.GetAdsRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;

public class GetAdsRequest extends BaseDeviceRequest{
	private String maxNumber;
	public GetAdsRequest(String maxNumber,String token,String phoneNumber){
		this.maxNumber=maxNumber;
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public BaseRequestHandler getHandler() {
		return new GetAdsRequestHandler(this);
	}
	public String getNumber() {
		return maxNumber;
	}
	
	public String getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(String maxNumber) {
		this.maxNumber = maxNumber;
	}
	
	public void setNumber(String number) {
		this.maxNumber = number;
	}
	
}
