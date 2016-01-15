package net.hamtag.server;

import net.hamtag.server.api.request.types.ads.GetAdsByCategoryForDeviceRequest;

public class Main {
	public static void main(String[] args) {
		//TODO: change location parameter for ad to float
		System.err.println(new GetAdsByCategoryForDeviceRequest("2", "SPORTS","0").getHandler().handle().toString());
	}
}
