package net.hamtag.server.api.request.types.ads;

import net.hamtag.server.api.request.BaseRequest;
import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.handler.ads.GetAdsByCategoryForDeviceRequestHandler;

public class GetAdsByCategoryForDeviceRequest extends BaseRequest{
	private String maxNumber,categories,lastUpdateTime;
	public GetAdsByCategoryForDeviceRequest(String maxNumber,String categories,String lastupdateTime){
		this.maxNumber=maxNumber;
		this.categories=categories;
		this.lastUpdateTime=lastupdateTime;
	}
	@Override
	public BaseRequestHandler getHandler() {
		return new GetAdsByCategoryForDeviceRequestHandler(this);
	}
	public String getNumber() {
		return maxNumber;
	}
	public void setNumber(String number) {
		this.maxNumber = number;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
