package net.hamtag.server.api.request.types.ads;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.handler.ads.GetAdsByTimeRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;

public class GetAdsByTimeRequest extends BaseDeviceRequest{
	private String maxNumber,lastUpdateTime;
	public GetAdsByTimeRequest(String maxNumber,String lastupdateTime,String token,String phoneNumber){
		this.maxNumber=maxNumber;
		this.lastUpdateTime=lastupdateTime;
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public BaseRequestHandler getHandler() {
		return new GetAdsByTimeRequestHandler(this);
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

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
