package net.hamtag.server.api.request.types.ads;

import net.hamtag.server.api.request.BaseRequest;
import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.handler.ads.AdInfoRequestHandler;

public class AdInfoRequest extends BaseRequest{
	private String testString;
	public AdInfoRequest(String t){
		testString=t;
	}
	
	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}

	@Override
	public BaseRequestHandler getHandler() {
		return new AdInfoRequestHandler(this);
	}

}
